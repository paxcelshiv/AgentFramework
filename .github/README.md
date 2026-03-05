# CI/CD Pipeline Setup Guide

This directory contains the GitHub Actions workflow configurations for automated testing and deployment of the Demoblaze Automation Framework.

## Quick Start

### 1. Review Workflows
```
.github/workflows/
├── demoblaze-tests.yml              # Main test execution (Windows, Java 11/17/21)
├── demoblaze-tests-headless.yml     # Linux headless testing
├── code-quality.yml                 # Build verification & code analysis
├── release.yml                      # Release management
└── CI-CD-PIPELINE.md               # Detailed documentation
```

### 2. Enable GitHub Actions
1. Go to repository Settings
2. Actions → General → Workflow permissions
3. Select **Read and write permissions**
4. Save

### 3. First Commit
Push the workflows to your repository:
```bash
git add .github/workflows/
git commit -m "ci: add GitHub Actions pipelines"
git push origin main
```

The workflows will automatically trigger!

## Workflows Overview

| Workflow | Trigger | Purpose | Platform |
|----------|---------|---------|----------|
| **demoblaze-tests.yml** | Push, PR, Schedule, Manual | Run full test suite with multiple Java versions | Windows |
| **demoblaze-tests-headless.yml** | Push, PR, Manual | Fast headless testing with PR comments | Ubuntu |
| **code-quality.yml** | Push, PR, Manual | Code analysis, build verification, SonarQube | Ubuntu |
| **release.yml** | Tags (v*), Manual | Create releases and publish artifacts | Ubuntu |

## Configuration

### Step 1: No Configuration Needed (Basic)
The workflows work out-of-the-box! Tests will run on every push and PR.

### Step 2: Optional - Add SonarQube (Advanced)
For code quality scanning:

1. **Create SonarCloud Account**:
   - Go to https://sonarcloud.io
   - Sign in with GitHub
   - Create organization/project

2. **Add Token Secret**:
   ```bash
   gh secret set SONAR_TOKEN --body "your-token-from-sonarcloud"
   ```
   Or via GitHub UI: Settings → Secrets → New repository secret

3. **Create sonar-project.properties**:
   ```properties
   sonar.projectKey=com.demoblaze:demoblaze-automation
   sonar.sources=src/main
   sonar.tests=src/test
   sonar.java.source=11
   ```

## Running the Workflows

### Workflow 1: Test on Push (Automatic)
```bash
# Any commit triggers tests
git commit -m "Fix test"
git push origin main
# → Tests run automatically on Windows (Java 11, 17, 21)
# → Headless tests run on Ubuntu
# → Code quality checks run
```

### Workflow 2: Test on Pull Request (Automatic)
```bash
# PR to main/develop triggers workflow
git push origin feature/new-feature
# → GitHub creates PR
# → All status checks run automatically
# → Results visible in PR checks tab
```

### Workflow 3: Scheduled Daily (Automatic)
```
Every day at 2 AM UTC:
→ Full test suite runs
→ Results available in Actions tab
→ Failures trigger notifications
```

### Workflow 4: Manual Dispatch (Optional)
```
GitHub UI: Actions → Select Workflow → Run Workflow
Options:
  - debug_enabled: true  (launches interactive terminal)
  - java-version: 17    (specific version)
```

### Workflow 5: Release Creation (Tag Push)
```bash
# Create and push version tag
git tag -a v1.0.0 -m "Release 1.0.0"
git push origin v1.0.0
# → Release workflow runs
# → GitHub release created with artifacts
# → JAR file available for download
```

## Viewing Results

### GitHub UI
1. **Actions Tab**: All workflow runs
2. **Pull Requests**: Status checks and PR comments
3. **Releases**: Published versions with artifacts
4. **Commits**: Status badges next to commits

### Artifacts
After workflow runs, download:
- **Screenshots**: `screenshots-jdk-{version}/` (30 days retention)
- **Logs**: `logs-jdk-{version}/` (30 days retention)
- **Test Results**: JUnit XML reports

Example:
```
Path: Actions → demoblaze-tests → Artifacts
├── screenshots-jdk-11
├── screenshots-jdk-17
├── logs-jdk-11
└── test-results-jdk-21
```

## Troubleshooting

### Tests Timeout

**Error**: `TimeoutError: Timeout 10000ms exceeded`

**Fix**: Update `TestConfig.java`
```java
// Line 13 - Change from:
public static final int PAGE_LOAD_TIMEOUT = 10000;
// To:
public static final int PAGE_LOAD_TIMEOUT = 30000;
```

Then commit and push - workflow will retry with new timeout.

### Playwright Binary Download Fails

**Error**: `Browser binaries not found`

**Fix**: The workflow includes installation step. If it still fails:

1. Check internet connection in GitHub Actions
2. Verify Maven permissions
3. Try manual trigger in Actions tab

### Tests Pass Locally but Fail in CI

**Common Cause**: Network latency or timing issues

**Fix**: 
- Increase timeouts
- Add more wait conditions
- Review CI logs for specific errors

### SonarQube Not Running

**Reason**: SONAR_TOKEN not configured

**Fix**: 
1. Add token to secrets (see Configuration Step 2)
2. Or remove/comment `code-quality` job if not needed

### PR Comment Not Appearing

**Reason**: Missing GitHub token permissions

**Fix**: Settings → Actions → General → Workflow permissions → Select "Read and write"

## Best Practices

### ✅ Recommended
- ✅ Run tests on every pull request
- ✅ Test multiple Java versions
- ✅ Require status checks before merge
- ✅ Review code quality metrics
- ✅ Use semantic versioning for releases (v1.0.0)
- ✅ Keep detailed commit messages

### ❌ Avoid
- ❌ Committing secrets/tokens in code
- ❌ Disabling status checks for merging
- ❌ Ignoring test failures
- ❌ Overriding branch protection rules

## Branch Protection Rules (Recommended)

For `main` branch, set:

```
✅ Require status checks to pass:
   ✓ build / Build Verification
   ✓ test-compile / Test Compilation  
   ✓ code-quality / Code Quality Analysis

✅ Require pull request reviews: 1 approval

✅ Require conversation resolution before merging

✅ Require branches to be up to date
```

**Setup**:
1. Go to Settings → Branches → Add rule
2. Branch name pattern: `main`
3. Configure options above
4. Save

## Performance Tips

### Speed Up Tests
- Headless mode: 30% faster than GUI
- Parallel execution: Multiple Java versions simultaneously
- Maven cache: Automatically used, 2-5x faster builds

### Reduce Costs
- Artifact retention: Set to 30 days (not indefinite)
- Only test on `main` and PRs to `develop`
- Disable debug mode for scheduled runs

## Integration with Other Tools

### Slack Notifications (Optional)
Create a Slack app, get webhook URL, then:
```bash
gh secret set SLACK_WEBHOOK --body "https://hooks.slack.com/services/..."
```

### Email Alerts
GitHub automatically sends email on workflow failure. Enable in:
Settings → Notifications → Workflow alerts

### Jira Integration
Add to workflow:
```yaml
- name: Jira Comment
  uses: atlassian/gajira-comment@master
  with:
    issue: PROJ-123
    comment: "Tests completed: ${{ job.status }}"
```

## Detailed Documentation

For complete workflow details, see: [`CI-CD-PIPELINE.md`](CI-CD-PIPELINE.md)

Topics covered:
- Detailed job breakdowns
- Environment configuration
- SonarQube setup instructions
- Release management guide
- Debugging procedures
- Performance optimization
- Azure DevOps/GitLab CI/Jenkins examples

## Support

- GitHub Actions Docs: https://docs.github.com/en/actions
- Playwright Java: https://playwright.dev/java/
- Maven: https://maven.apache.org/
- JUnit: https://junit.org/junit4/

---

**Status**: ✅ Ready to Deploy
**Last Updated**: March 5, 2026
**Version**: 1.0.0
