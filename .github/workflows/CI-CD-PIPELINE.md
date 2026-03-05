# GitHub Actions CI/CD Pipeline

This document describes the automated CI/CD pipelines for the Demoblaze Automation Framework using GitHub Actions.

## Overview

The CI/CD pipeline consists of 4 main workflows:

1. **Demoblaze Automation Tests** - Main test execution pipeline
2. **Demoblaze Tests - Headless Mode** - Ubuntu-based headless testing
3. **Code Quality & Build Verification** - Code analysis and build checks
4. **Release and Deploy** - Release management and artifact publishing

---

## 1. Demoblaze Automation Tests (`demoblaze-tests.yml`)

### Purpose
Executes the full Demoblaze automation test suite on Windows with multiple Java versions.

### Trigger Events
- **Push**: To branches `main`, `develop`, `feature/**`
- **Pull Request**: To branches `main`, `develop`
- **Schedule**: Daily at 2 AM UTC (cron job)
- **Manual**: Workflow dispatch with optional debug mode

### Matrix Strategy
Tests run on:
- Java 11, 17, and 21 (parallel execution)
- Windows Latest OS
- All variations run simultaneously

### Jobs Breakdown

#### `test` Job
| Step | Action | Purpose |
|------|--------|---------|
| 1 | Checkout Code | Clone repository with full history |
| 2 | Set up JDK | Install specified Java version |
| 3 | Install Browsers | Download Playwright browser binaries |
| 4 | Build & Test | Compile and run Maven tests |
| 5 | Generate Report | Process test results |
| 6 | Upload Screenshots | Store test artifacts (30 days) |
| 7 | Upload Logs | Store execution logs (30 days) |
| 8 | Upload Results | Store JUnit XML reports (30 days) |
| 9 | Publish Results | Display results in GitHub UI |
| 10 | Test Summary | Print summary statistics |
| 11 | Debug Mode | Optional tmate session if enabled |

#### `report` Job
- Consolidates results from all matrix runs
- Generates combined test report summary
- Displays test metrics and artifacts

#### `notify` Job
- Triggers only if tests fail
- Sends failure notifications
- Provides links to failed run details

### Environment Variables
```yaml
# Default: Inherited from GitHub Actions context
JAVA_HOME        # Set by actions/setup-java
PATH             # Maven bin added automatically
GITHUB_WORKSPACE # Repository checkout directory
```

### Artifacts Generated
- **Screenshots**: `screenshots-jdk-{version}/` (UTC timestamp)
- **Logs**: `logs-jdk-{version}/` (UTC timestamp)
- **Test Results**: `test-results-jdk-{version}/` (JUnit XML)

### Example Run

```bash
# Triggers on push to feature/new-tests
git push origin feature/new-tests
# → Runs tests on Java 11, 17, 21

# Manual trigger with debug
# Click "Run workflow" on GitHub Actions tab
# Enable debug_enabled: true
# → Same tests + interactive terminal session
```

### Debugging Failed Tests

1. **Access Artifacts**:
   - Go to workflow run
   - Download screenshots/logs/results
   - Review test execution flow

2. **Enable Debug Mode**:
   - Go to "Run workflow" → input debug_enabled=true
   - Tests run with tmate (interactive SSH terminal)
   - Debug live while tests run

3. **Check Logs**:
   - Search for "SCENARIO" in demoblaze-automation.log
   - Look for "Error in Scenario" for failures
   - Check timeout errors

---

## 2. Demoblaze Tests - Headless Mode (`demoblaze-tests-headless.yml`)

### Purpose
Runs tests in headless mode (no UI) on Ubuntu for faster CI/CD execution.

### Trigger Events
- **Push**: Changes to `src/`, `pom.xml`, or workflow file
- **Pull Request**: To `main`, `develop`
- **Manual**: Workflow dispatch

### Configuration
- OS: Ubuntu Latest
- Java: 11
- Browser: Headless Chromium
- Run Time: Faster than GUI mode

### Unique Features

#### PR Comments
Automatically comments on PRs with:
- ✅ Passed/❌ Failed test counts
- Links to screenshots and logs
- Direct artifacts access

#### Test Reporter Integration
- Parses JUnit XML results
- Displays tests in PR checks tab
- Shows inline test failure details

### Usage

```bash
# Triggers on commit to main
git push origin main
# → Runs headless tests on Ubuntu

# Check results on PR
# → View "Headless Test Report" in checks
# → Click for detailed results
```

---

## 3. Code Quality & Build Verification (`code-quality.yml`)

### Purpose
Performs code analysis, compilation checks, and documentation validation.

### Trigger Events
- **Push**: To `main`, `develop`
- **Pull Request**: To `main`, `develop`
- **Manual**: Workflow dispatch

### Jobs Overview

#### `build` Job
- Compiles project without tests
- Runs SpotBugs (static analysis)
- Checks for dependency issues

#### `test-compile` Job
- Compiles test classes
- Counts generated test class files
- Verifies test code quality

#### `code-quality` Job
- Full project build
- **SonarQube Integration** (if token configured):
  - Code smell detection
  - Security vulnerability scanning
  - Test coverage analysis
  - Technical debt estimation

#### `docs` Job
- Verifies README.md exists
- Checks pom.xml integrity
- Validates project structure

### SonarQube Integration

#### Setup Instructions

1. **Create SonarCloud Account**:
   - Visit https://sonarcloud.io
   - Sign in with GitHub
   - Authorize organization

2. **Configure Secrets**:
   ```
   Repository Settings → Secrets and Variables → Actions
   Add SONAR_TOKEN = <token from SonarCloud>
   ```

3. **Create sonar-project.properties**:
   ```properties
   sonar.projectKey=com.demoblaze:demoblaze-automation
   sonar.sources=src/main
   sonar.tests=src/test
   sonar.java.source=11
   sonar.coverage.exclusions=**/tests/**
   ```

4. **Results**:
   - Quality gate status in PR checks
   - Detailed analysis at sonarcloud.io dashboard
   - Coverage reports and trends

---

## 4. Release and Deploy (`release.yml`)

### Purpose
Automates release creation and artifact publishing.

### Trigger Events
- **Tag Push**: Any tag starting with `v` (e.g., `v1.0.0`)
- **Manual**: Workflow dispatch with version input

### Release Process

#### Step 1: Create Tag
```bash
# Create and push version tag
git tag -a v1.0.0 -m "Release 1.0.0"
git push origin v1.0.0
# → Automatically triggers release workflow
```

#### Step 2: Build Release
- Compiles JAR artifact
- Generates release notes
- Creates checksums

#### Step 3: Publish
- Creates GitHub release page
- Uploads:
  - `demoblaze-automation-1.0.0.jar`
  - `README.md`
  - `pom.xml`
  - Checksum file
- Retention: 90 days

#### Step 4: Access Release
```bash
# GitHub Releases page shows:
# - Release title: v1.0.0
# - Release notes with changelog
# - Downloadable artifacts
# - Checksums for verification
```

#### Verify Release
```bash
# Download and verify integrity
sha256sum -c CHECKSUMS.txt

# Output should show:
# demoblaze-automation-1.0.0.jar: OK
```

---

## Workflow Configuration

### GitHub Secrets Required

```yaml
# For SonarQube analysis (optional)
SONAR_TOKEN        # SonarCloud authentication token

# For deployments (optional)
DEPLOY_KEY         # SSH key for deployment server
DEPLOY_HOST        # Deployment server address
```

### Set Secrets
```bash
# Via GitHub CLI
gh secret set SONAR_TOKEN --body "your-token"
gh secret set DEPLOY_KEY --body "$(cat ~/.ssh/id_rsa)"

# Via GitHub UI
Settings → Secrets and Variables → Actions → New repository secret
```

### Branch Protection Rules

Recommended for `main`:
```
✅ Require status checks to pass before merging:
   - build / Build Verification
   - test-compile / Test Compilation
   - code-quality / Code Quality Analysis
   - headless-test / Headless Test Execution

✅ Require code reviews before merging

✅ Require PR comments from tests
```

---

## Monitoring and Notifications

### GitHub UI
- **Actions Tab**: View all workflow runs
- **Commits**: See status badges next to commits
- **Pull Requests**: Check run status in PR

### Email Notifications
GitHub automatically emails on:
- Workflow failures (if enabled in settings)
- PR review requests
- Release publications

### Custom Notifications (Optional)

#### Slack Integration
```yaml
- name: Slack Notification
  uses: slackapi/slack-github-action@v1
  with:
    webhook-url: ${{ secrets.SLACK_WEBHOOK }}
    payload: |
      {
        "text": "Test run completed: ${{ job.status }}"
      }
```

#### Email via GitHub
Settings → Notifications → Workflow alerts

---

## Troubleshooting

### Tests Timeout in CI

**Problem**: Tests timeout after 10 seconds in GitHub Actions

**Solution**: Increase timeouts in `TestConfig.java`:
```java
public static final int PAGE_LOAD_TIMEOUT = 30000;
public static final int ELEMENT_WAIT_TIMEOUT = 15000;
```

### Playwright Binary Download Fails

**Problem**: "Browser binaries not found" error

**Solution**: 
```yaml
- name: Install Playwright Browsers
  run: |
    mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install" -q
```

### Maven Dependency Resolution Issues

**Problem**: "Cannot resolve dependency"

**Solution**: Clear Maven cache
```yaml
- name: Clear Maven Cache
  run: mvn dependency:purge-local-repository -Dexclude=com.microsoft.playwright:*
```

### SonarQube Analysis Fails

**Problem**: "No sonar token provided"

**Solution**: Add SONAR_TOKEN to repository secrets (or disable job)

### GitHub Token Permissions

**Problem**: "Insufficient permissions" in release/comments

**Solution**: GitHub Actions token is auto-created with default permissions. Verify in:
Settings → Actions → General → Workflow permissions → Read/Write access

---

## Performance Optimization

### Cache Maven Dependencies
```yaml
- uses: actions/setup-java@v4
  with:
    cache: maven  # ← Already configured in workflows
```
**Effect**: 2-5x faster builds on subsequent runs

### Parallel Matrix Execution
```yaml
strategy:
  matrix:
    java-version: ['11', '17', '21']  # ← Runs in parallel
```
**Effect**: Multiple versions tested simultaneously

### Headless Mode
- Headless mode is 30% faster than GUI
- Use for CI/CD, GUI mode for local development

---

## Best Practices

### ✅ Do's
- Run tests on every commit
- Test multiple Java versions
- Require status checks before merge
- Keep logs for 30 days
- Tag releases semantically (v1.0.0)
- Review code quality metrics

### ❌ Don'ts
- Don't commit credentials in workflows
- Don't use `continue-on-error: true` for critical steps
- Don't skip security checks for "speed"
- Don't retain artifacts indefinitely (storage cost)
- Don't release without running tests

---

## Integration Examples

### Azure DevOps
```yaml
trigger:
  - main
  
pool:
  vmImage: 'windows-latest'

steps:
  - task: JavaToolInstaller@0
    inputs:
      versionSpec: '11'
```

### GitLab CI
```yaml
stages:
  - test

test_job:
  stage: test
  image: maven:3.9-openjdk-11
  script:
    - mvn clean test
```

### Jenkins
```groovy
pipeline {
    agent { label 'windows' }
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
```

---

## Support & Resources

- GitHub Actions Docs: https://docs.github.com/en/actions
- Maven Docs: https://maven.apache.org/
- Playwright Java: https://playwright.dev/java/
- SonarQube: https://docs.sonarcloud.io/

---

**Last Updated**: March 5, 2026
**Framework Version**: 1.0.0
**Status**: ✅ Ready for Production
