# java-library-template 🎨

[![Use this template](https://img.shields.io/badge/from-java--library--template-brightgreen?logo=dropbox)](https://github.com/thriving-dev/java-library-template/generate)
[![Java CI](https://github.com/thriving-dev/java-library-template/actions/workflows/1.pipeline.yml/badge.svg)](https://github.com/thriving-dev/java-library-template/actions/workflows/1.pipeline.yml)
[![Maven Central](https://img.shields.io/maven-central/v/dev.thriving.oss/java-library-template.svg)](https://central.sonatype.com/artifact/dev.thriving.oss/java-library-template)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://thriving-dev.github.io/java-library-template/javadoc/)
[![CC BY-NC-SA 4.0](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-sa/4.0/)

_TLDR:_ Java Library GitHub Template Repository

## Features
- 🥷 One-click automated **!! INITIAL: Migrate Repo Template !!** (GitHub Action)
- **Java 21 🤝 Gradle Kotlin DSL**, version catalog
- **GitHub Actions CI/CD** pipeline 👷
- 🚀 **One-click release** process + **publish** to **Maven Central**
- **Security & 🚦 Vulnerability scan** with **[trivy](https://github.com/aquasecurity/trivy)** & GitHub CodeQL Analysis
- **Automated dependency updates** with **[Renovate](https://github.com/renovatebot/renovate)** 🤖
- Javadoc deployed with GitHub Pages
- Open Source Community ready (Code of Conduct, Contribution guidelines, Issue & PR Templates)

## Quick Start
1. [Use this template](https://github.com/thriving-dev/java-library-template/generate) to create your own repository
2. Manually trigger '[!! INITIAL: Migrate Repo Template !!](https://github.com/thriving-dev/java-library-template/actions/workflows/0.initial.migrate-repo-template.yml)' action
   > ℹ️ This workflow automatically 'migrates' all files in your new repository, updating the **gradle project group**, **module name**, **package names**, and **all references** to the repo `<owner>/<name>`.
   >
   > - Head over to **Actions** (1)
   > - on the left-hand side select the topmost workflow '**!! INITIAL: Migrate Repo Template !!**' (2)
   > - click the **Run workflow** button (3)
   > - **fill out** the form & **start** the pipeline (4)(5)
   > ___
   > ![image](https://github.com/thriving-dev/java-library-template/assets/10864443/41a380d5-e521-4050-9296-9f5bee4088e6)

3. One-off chores
   * Choose & update the LICENSE, [here](LICENSE)
   * Update _Maven Publication_ details [here](java-library-template/build.gradle.kts#L6-L13)
   * [Configure GitHub Pages](#prerequisites-configure-github-pages) to deploy branch 'gh-pages' (Javadoc)
   * Add secrets required for [publishing to Maven Central](#prerequisites-sonatype-credentials--gpg-signing-key)
   * Create and provide a PAT required for [release pipeline](#prerequisites-pat-provided-as-cigithubtoken)
   * Install & configure renovate app ([instructions](#prerequisites-enable--configure-renovate))

## Project Structure
_TODO_

## CI/CD Pipeline


## Publish to Maven Central
### Use
_TODO_

### Prerequisites: Sonatype Credentials & GPG Signing Key
The initial setup for your OSSRH repository requires some manual steps and human review (see why), after which your deployment process is typically modified to get components into OSSRH. These are all one time steps.
I recommend to follow the [official guide](https://central.sonatype.org/publish/publish-guide/).

In order to deploy your components to OSSRH with Gradle, you have to meet the [requirements](https://central.sonatype.org/publish/requirements/) for your metadata in the pom.xml as well as supply the required, signed components.

> ℹ️ The publish process uses [io.github.gradle-nexus.publish-plugin](https://plugins.gradle.org/plugin/io.github.gradle-nexus.publish-plugin) under the hood.

The gradle project as well as the CI/CD pipeline is already fully prepared for the publish process.
The GH actions job [callable.publish-sonatype.yml](.github/workflows/callable.publish-sonatype.yml) requires following Secrets:  

| Secret name              | Value                                                                                                                                                                                                                         |
|--------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| `OSSRH_USERNAME`         | The username of your OSSRH account.                                                                                                                                                                                           |
| `OSSRH_PASSWORD`         | The password of your OSSRH account.                                                                                                                                                                                           |
| `GPG_SIGNING_KEY`        | The GPG private key to sign your artifacts (in ascii-armored format). You can obtain it with `gpg --armor --export-secret-keys <your@email.here>` or you can create one key online on [pgpkeygen.com](https://pgpkeygen.com). |
| `GPG_SIGNING_PASSPHRASE` | The passphrase for unlocking the secret key. (you picked it when creating the key).                                                                                                                                           |

Please define the secrets via your repository settings. ([Settings > Security > Secrets and variables > Actions](settings/secrets/actions))
![image](https://github.com/thriving-dev/java-library-template/assets/10864443/e6cf928c-6665-43fc-9506-c29d210b18de)

## Release Process
### Use
_TODO_

### Prerequisites: PAT provided as `CI_GITHUB_TOKEN`
_TODO_

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
The libraries gradle dependencies are scanned for known [CVE](https://www.cve.org/) with **[aquasecurity/trivy](https://github.com/aquasecurity/trivy)**. The scan results can be reviewed and managed under [Security > Vulnerability alerts > Code scanning](https://github.com/thriving-dev/java-library-template/security/code-scanning).

Scans are triggered 
1. with each main CI/CD pipeline run
2. Scheduled (weekly) ([ref](.github/workflows/2.scheduled.code-analysis.yml))

Please refer to [official GitHub documentation](https://docs.github.com/en/code-security/code-scanning/introduction-to-code-scanning/about-code-scanning) for more details.

## Automated Dependency Updates with Renovate
### Dependency Dashboard

![image](https://github.com/thriving-dev/java-library-template/assets/10864443/3300d418-8dee-4071-96df-dc53882315fd)

### PRs created by renovate bot
![image](https://github.com/thriving-dev/java-library-template/assets/10864443/bcd03d8f-f620-4344-85d8-5f29d28be030)

### Prerequisites: Enable & Configure Renovate
This template ships with a prepared [renovate.json](renovate.json). 

The recommended way to enable renovate is to use the [Renovate GitHub App](https://github.com/apps/renovate).


## Credits
- inspired by https://github.com/cortinico/kotlin-android-template
- PR & issue templates copied / adapted from https://github.com/nuxt/nuxt


<p xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/"><a property="dct:title" rel="cc:attributionURL" href="https://github.com/thriving-dev/java-library-template">java-library-template</a> by <a rel="cc:attributionURL dct:creator" property="cc:attributionName" href="https://thriving.dev">thriving.dev</a> is licensed under <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/?ref=chooser-v1" target="_blank" rel="license noopener noreferrer" style="display:inline-block;">CC BY-NC-SA 4.0</a></p>
