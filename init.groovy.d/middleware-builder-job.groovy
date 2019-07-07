#!groovy

/* PHP7 fpm Image build job */
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import jenkins.model.Jenkins
import hudson.plugins.git.*;

def scm = new GitSCM("https://github.com/ansilh/demo-middleware.git")
scm.branches = [new BranchSpec("*/master")];

def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(scm, "Jenkinsfile")

def parent = Jenkins.instance
def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(parent, "Middleware-k8s-builder")
job.definition = flowDefinition

parent.reload()