# java-library-template 🎨

[![Use this template](https://img.shields.io/badge/from-java--library--template-brightgreen?logo=dropbox)](https://github.com/thriving-dev/java-library-template/generate)
[![Java CI](https://github.com/thriving-dev/java-library-template/actions/workflows/1.pipeline.yml/badge.svg)](https://github.com/thriving-dev/java-library-template/actions/workflows/1.pipeline.yml)
[![Maven Central](https://img.shields.io/maven-central/v/dev.thriving.oss/java-library-template.svg)](https://central.sonatype.com/artifact/dev.thriving.oss/java-library-template)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://thriving-dev.github.io/java-library-template/javadoc/)
[![CC BY-NC-SA 4.0](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-sa/4.0/)

_TLDR:_ Java Library GitHub Template Repository

* [<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/10864443/270873951-641a38c4-10e7-4b75-8dc0-1b5d473f07b3.svg" width="20"> Introducing 'java-library-template' in 60s - YouTube](https://www.youtube.com/watch?v=nXs7hSV6ris&list=PL1tfxqbktkFYK5zYjfHxt4wFOus5R9Y3g)   
* https://thriving.dev/blog/java-library-development-get-started-quickly-with-java-library-template


## Features
- 🥷 One-click **automated initial project migration workflow** (GitHub Action)
- **Java 21** (corretto) 🤝 **Gradle Kotlin DSL**, version catalog
- **GitHub Actions CI/CD pipeline**, 👷 efficient build pipeline, caching, integration tests, test report & failed test annotations
- 🚀 **One-click release** process + **publish** to **Maven Central**
- **Security & 🚦 Vulnerability scan** with **[trivy](https://github.com/aquasecurity/trivy)** & GitHub CodeQL Analysis
- **Automated dependency updates** with **[Renovate](https://github.com/renovatebot/renovate)** 🤖
- **Javadoc** deployed with **GitHub Pages**
- Open Source **Community ready** (Code of Conduct, Contribution guidelines, Issue & PR Templates)


## Quick Start
**Demo on YouTube**: [<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/10864443/270873951-641a38c4-10e7-4b75-8dc0-1b5d473f07b3.svg" width="20"> java-library-template 1/7: Getting started (generate from; create PAT, one-click migrate)](https://www.youtube.com/watch?v=lDfg9D0A-nA&list=PL1tfxqbktkFYK5zYjfHxt4wFOus5R9Y3g)
1. [Use this template](https://github.com/thriving-dev/java-library-template/generate) to create your own repository
2. Create & provide a PAT (Personal Access Token) for the CI/CD pipeline

   Three workflows commit and push changes to the repository and therefore require additional permissions. ('migrate-repo-template', 'publish-javadoc', 'gradle-release')

   The jobs expect a secret by the name `CI_GITHUB_TOKEN` that holds a PAT with _write_ permission for _Content_.

   To create a new access token, the following steps are required (ref [GitHub documentation](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-fine-grained-personal-access-token)):
   1. If the new repo owner is an organisation, **enrol** the organisation for '**Fine-grained personal access tokens**'. In the organisation '**Settings > Third-party Access > Personal access tokens**'.
   2. Head to the [Developer settings](https://github.com/settings/tokens?type=beta) and **enrol** your personal account for the new '**Fine-grained personal access tokens**'. (That's a one-off for your account and you might already have done this before)
   3. Next, click on the button '**Generate new token**' and create a token for the target _**Resource owner**_, with access to your project and the following '**Repository Permissions**'
      * **Contents:** **Read** and **Write** access to code
      * **Metadata:** **Read** access to metadata

      <img width="680" alt="image" src="https://github.com/thriving-dev/java-library-template/assets/10864443/ff00fec5-36b5-46b3-9fae-83504aa00199">

   4. Provide your new PAT either as an Organisation secret or a Repository secret with the name `CI_GITHUB_TOKEN`.

      <img width="800" src="https://github.com/thriving-dev/java-library-template/assets/10864443/9a9224ef-ef58-4c59-9025-5d83ac5981b9">

3. Trigger the '**!! INITIAL: Migrate Repo Template !!**' workflow

   ℹ️ This workflow automatically 'migrates' all files in your new repository, updating the **gradle project group**, **module name**, **package names**, and **all references** to the repo `<owner>/<name>`.

   - Head over to **Actions** (1)
   - on the left-hand side select the topmost workflow '**!! INITIAL: Migrate Repo Template !!**' (2)
   - click the **Run workflow** button (3)
   - **fill out** the form & **start** the pipeline (4)(5)

   ![image](https://github.com/thriving-dev/java-library-template/assets/10864443/41a380d5-e521-4050-9296-9f5bee4088e6)

4. Final one-off tasks
   * Choose & update the LICENSE, [here](LICENSE)
   * Update _Maven Publication_ details [here](java-library-template/build.gradle.kts#L6-L13)
   * [Configure GitHub Pages](#prerequisites-configure-github-pages) to deploy branch 'gh-pages' (Javadoc)
   * Add secrets required for [publishing to Maven Central](#prerequisites-sonatype-credentials--gpg-signing-key)
   * Install & configure renovate app ([instructions](#prerequisites-enable--configure-renovate))

## Project Structure
The project template consists of three top-level _folders_:
* `.github/`: Defines the GitHub Actions CI tasks and templates for new pull requests, issues, etc.
* `gradle/`: Contains Gradle Configuration files such as the Gradle [Version Catalog](https://docs.gradle.org/current/userguide/platforms.html) and the Gradle Wrapper.
* `java-library-template/`: The library source code (Gradle sub-project).

In addition, following _files_ are worth highlighting:
* `gradle/libs.versions.toml`: A [conventional file](https://docs.gradle.org/current/userguide/platforms.html#sub:conventional-dependencies-toml) to declare a version catalog.
* `settings.gradle.kts`: The multi-project Gradle settings file. Here are all the sub-projects defined.
* `gradle.properties`: Holds the library version, needed & maintained by the CI/CD pipeline [release process](#release-process).
* `**/build.gradle.kts`: Gradle build file

## CI/CD Pipeline
The heart of this template is the 'Main GitHub Actions CI/CD Pipeline'. See it in [Actions](https://github.com/thriving-dev/java-library-template/actions/workflows/1.pipeline.yml) (👻).

![image](https://github.com/thriving-dev/java-library-template/assets/10864443/8e5436c3-f807-4617-9e77-6d21e9dfb7c2)

The workflow encompasses multiple jobs, modelled and linked with dependencies and conditions.
Based on the context (trigger, ref, input arguments) it meets different use cases:
1. **Check**. Build, test, integration test; code quality & vulnerability scans.    
   Runs for active PRs - as well as part of all subsequent listed use cases.
2. **Latest**. 'Check', publish SNAPSHOT version to Maven Central and Javadoc (GitHub Pages).   
   Runs on pushes to the main branch.
3. **Release Process**. 'Check', executes (major|minor|patch) release process via the Gradle plugin.   
   Manually triggered workflow via GitHub UI/API.
3. **Library Release**. 'Check', publish RELEASE version to Maven Central and Javadoc (GitHub Pages).   
   Runs for pushed tags.

## Publish to Maven Central
### Automated Process
The Maven publish process is fully automated and does not require manual action.
- The _main_ branch (per process definition) always is set to the next [SNAPSHOT version](gradle.properties) and is published to the Sonatype snapshot repository with each main CI/CD pipeline run. The pipeline runs e.g. when a PR is merged, but can also be triggered manually.
- Release deployment happens when a new tag is pushed to GitHub. (Part of the [release process](#release-process))

### Prerequisites: Sonatype Credentials & GPG Signing Key
The initial setup for your OSSRH repository requires some manual steps and human review (see why), after which your deployment process is typically modified to get components into OSSRH. These are all one time steps.
I recommend to follow the [official guide](https://central.sonatype.org/publish/publish-guide/).

In order to deploy your components to OSSRH with Gradle, you have to meet the [requirements](https://central.sonatype.org/publish/requirements/) for your metadata in the pom.xml as well as supply the required, signed components.

> ℹ️ The publish process uses [io.github.gradle-nexus.publish-plugin](https://plugins.gradle.org/plugin/io.github.gradle-nexus.publish-plugin) under the hood.

The gradle project as well as the CI/CD pipeline is already fully prepared for the publishing process.
The GH actions job [callable.publish-sonatype.yml](.github/workflows/callable.publish-sonatype.yml) requires the following Secrets:

| Secret name              | Value                                                                                                                                                                                                                         |
|--------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| `OSSRH_USERNAME`         | ~The username of your OSSRH account.~ The first part of your OSSRH (Sonatype) user access token.                                                                                                                              |
| `OSSRH_PASSWORD`         | ~The password of your OSSRH account.~ The second part of your OSSRH (Sonatype) user access token.                                                                                                                             |
| `GPG_SIGNING_KEY`        | The GPG private key to sign your artifacts (in ascii-armored format). You can obtain it with `gpg --armor --export-secret-keys <your@email.here>` or you can create one key online on [pgpkeygen.com](https://pgpkeygen.com). |
| `GPG_SIGNING_PASSPHRASE` | The passphrase for unlocking the secret key. (you picked it when creating the key).                                                                                                                                           |

Please define the secrets via your repository settings. (Settings > Security > Secrets and variables > Actions)
<img width="800" alt="Preview of Javadoc published to GitHub Pages by the CI/CD pipeline" src="https://github.com/thriving-dev/java-library-template/assets/10864443/e6cf928c-6665-43fc-9506-c29d210b18de">

## Release Process
### Creating a Release
To release a new version via the CI/CD Pipeline, please follow the instructions below.
- Navigate to Actions (1)
- \> Main Pipeline (2)
- Click 'Run workflow' button (3)
- Select a semver release type with the 'Release Library' dropdown (4)
- 'Run the workflow' (5)
  ![image](https://github.com/thriving-dev/java-library-template/assets/10864443/8008fefd-efc3-466d-806f-5cc33eb74b7f)

The release process includes
- Pipeline run (incl. build & tests) that executes the release plugin (6)
- The release plugin first sets & commits the new version (7a)
- Creates & pushes a new tag (7b)
- Sets the main branch to the next SNAPSHOT version (7c)
  ![image](https://github.com/thriving-dev/java-library-template/assets/10864443/dd7694c7-36f2-497e-b768-f9a76516bacb)

The new version is automatically published to Maven Central! 🚀
![image](https://github.com/thriving-dev/java-library-template/assets/10864443/89db0a69-c4ea-4f45-a655-8349d448a4c6)

### Prerequisites: PAT provided as `CI_GITHUB_TOKEN`
The CI/CD 'gradle-release' job expects a secret by the name `CI_GITHUB_TOKEN` that holds a PAT (Personal Access Token) with permission to push tags as part of the release process.

If you have been following the Quick Start guide you should already have this configured. Please refer to ['Quick Start' step 2](#quick-start).

## Javadoc
### Use
A Javadoc website of your library, generated by gradle, is 'published' to GitHub Pages by the CI/CD pipeline. In addition to each released version, the current snapshot version (_main_ branch) is published as `current`.    
-> visit the [live preview](https://thriving-dev.github.io/java-library-template/javadoc/).

<img width="680" alt="Preview of Javadoc published to GitHub Pages by the CI/CD pipeline" src="https://github.com/thriving-dev/java-library-template/assets/10864443/119e8055-2755-40d4-8ec4-69bdc2e5339b">

### Prerequisites: Configure GitHub Pages
To host the generated Javadoc, configure GitHub Pages for your repository to deploy from branch `gh-pages`. You can also find all deployments under ['pages-build-deployment'](https://github.com/thriving-dev/java-library-template/actions/workflows/pages/pages-build-deployment).

> ℹ️ The branch is created with the first CI/CD pipeline run. ('Publish javadoc' job)

![image](https://github.com/thriving-dev/java-library-template/assets/10864443/208d68b8-f955-4089-b41a-48a2ef263186)

## Security & CodeQL Analysis
### Common Vulnerabilities and Exposures (CVE)
The libraries gradle dependencies are scanned for known [CVE](https://www.cve.org/) with **[aquasecurity/trivy](https://github.com/aquasecurity/trivy)**. The scan results can be reviewed and managed under 'Security > Vulnerability alerts > Code scanning'.

Scans are triggered
1. with each main CI/CD pipeline run
2. Scheduled (weekly) ([ref](.github/workflows/2.scheduled.code-analysis.yml))

<img width="800" alt="Preview of a critical CVE listed in the GitHub Security 'Code scanning' overview page" src="https://github.com/thriving-dev/java-library-template/assets/10864443/2980ba31-32c4-4bd8-9adf-4d852806614b">

Please refer to [official GitHub documentation](https://docs.github.com/en/code-security/code-scanning/introduction-to-code-scanning/about-code-scanning) for more details.

## Automated Dependency Updates with Renovate

ℹ️ Renovate (RenovateBot) is a software tool that automates the process of keeping software dependencies up-to-date by scanning code repositories, identifying outdated dependencies, and generating automated pull requests to update them.

It’s Open Source and Community-Driven, supports a wide range of programming languages and package managers, and integrates with GitHub & Gitlab as well as other popular CI/CD systems.

### Dependency Dashboard

<img width="800" src="https://github.com/thriving-dev/java-library-template/assets/10864443/3300d418-8dee-4071-96df-dc53882315fd">

### PRs created by renovate bot
<img width="800" src="https://github.com/thriving-dev/java-library-template/assets/10864443/bcd03d8f-f620-4344-85d8-5f29d28be030">

### Prerequisites: Enable & Configure Renovate
This template ships with a prepared [renovate.json](renovate.json).

The recommended way to enable renovate is to use the [Renovate GitHub App](https://github.com/apps/renovate).


## Credits
- inspired by https://github.com/cortinico/kotlin-android-template
- PR & issue templates copied / adapted from https://github.com/nuxt/nuxt


<p xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/"><a property="dct:title" rel="cc:attributionURL" href="https://github.com/thriving-dev/java-library-template">java-library-template</a> by <a rel="cc:attributionURL dct:creator" property="cc:attributionName" href="https://thriving.dev">thriving.dev</a> is licensed under <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/?ref=chooser-v1" target="_blank" rel="license noopener noreferrer" style="display:inline-block;">CC BY-NC-SA 4.0</a></p>
