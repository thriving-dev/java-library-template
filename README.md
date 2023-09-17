# java-library-template 🎨

[![Use this template](https://img.shields.io/badge/from-java--library--template-brightgreen?logo=dropbox)](https://github.com/thriving-dev/java-library-template/generate)
[![Java CI](https://github.com/thriving-dev/java-library-template/actions/workflows/1.pipeline.yml/badge.svg)](https://github.com/thriving-dev/java-library-template/actions/workflows/1.pipeline.yml)
[![Maven Central](https://img.shields.io/maven-central/v/dev.thriving.oss/java-library-template.svg)](https://central.sonatype.com/artifact/dev.thriving.oss/java-library-template)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://thriving-dev.github.io/java-library-template/javadoc/)

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
3. One-off chores
   * Choose & update the LICENSE, [here](LICENSE)
   * Update _Maven Publication_ details [here](java-library-template/build.gradle.kts#L6-L13)
   * [Configure GitHub Pages](#TODO) to deploy branch 'gh-pages' (Javadoc)
   * Create and provide a PAT required for [release pipeline](#TODO)
   * Add secrets required for [publishing to Maven Central](#TODO)
   * Install & configure renovate app ([instructions](#install-renovate-app))

## Project Structure

## Credits
- inspired by https://github.com/cortinico/kotlin-android-template
- PR & issue templates copied / adapted from https://github.com/nuxt/nuxt
