# Pipeline Examples library

This folder contains a selection of example Jenkins pipelines based on the CI/CD services provided by the [hamlet bash executor](https://github.com/hamlet-io/executor-bash/)

Each pipeline performs a specific task in the CI/CD process and have been divided into standard categories

- build - Creating application artefacts based on application source code. This also includes testing of the code
- deploy - Deployment of the application artefacts generated by build pipelines into a product account
- manage - Management tasks completed by users against the product. This includes management of underlying infrastructure
- utilities - Minor tasks that can either be completed by users or automated to maintain the product deployment

## How to use the pipelines

Since we don't know what your Jenkins environment looks like we can't specify all of the possible options and parameters that you might want to use. Required values that need to be updated are listed as `< description >` in the pipeline

You can either copy these pipelines into jenkins job definitions or use SCM to retrieve the pipeline on invocation. In this case we recommend storing the deploy, manage and utilities pipelines in the CMDB and store the build pipelines in the application code repository. This is especially useful if you are using the [multi branch](https://www.jenkins.io/doc/book/pipeline/multibranch/) to handle branch and PR based CI/CD processes.
