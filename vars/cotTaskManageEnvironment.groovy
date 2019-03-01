#!/usr/bin/env groovy

def call( String propertiesFile ) {

    def productProperties = readProperties interpolate: true, file: propertiesFile;
    def environmentVariables = productProperties.collect {/$it.key=$it.value/ }

    sh '''#!/bin/bash
    trap \'exit ${RESULT:-1}\' EXIT SIGHUP SIGINT SIGTERM
    ${AUTOMATION_BASE_DIR}/setContext.sh
    RESULT=$?
    ''' 

    def contextProperties = readProperties interpolate: true, file: 'context.properties'
    environmentVariables += contextProperties.collect {/$it.key=$it.value/ }

    withEnv( environmentVariables ) {
        
        sh '''#!/bin/bash
        trap \'exit ${RESULT:-1}\' EXIT SIGHUP SIGINT SIGTERM
        ${AUTOMATION_DIR}/constructTree.sh
        RESULT=$?
        ''' 
    }

    contextProperties = readProperties interpolate: true, file: 'context.properties'
    environmentVariables += contextProperties.collect {/$it.key=$it.value/ }

    withEnv( environmentVariables ) {
        sh '''#!/bin/bash
        trap \'exit ${RESULT:-1}\' EXIT SIGHUP SIGINT SIGTERM
        ${AUTOMATION_DIR}/manageEnvironment.sh
        RESULT=$?
        '''
    }
}