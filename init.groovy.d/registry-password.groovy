#!groovy

// imports
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.*
import hudson.util.Secret
import jenkins.model.Jenkins


def regUsername = System.getenv("REGISTRY_USER_NAME")
def regPassword = System.getenv("REGISTRY_PASSWORD")
assert regUsername != null : "No REGISTRY_USER_NAME env var provided, but required"
assert regPassword != null : "No REGISTRY_PASSWORD env var provided, but required"

// parameters
def jenkinsKeyUsernameWithPasswordParameters = [
  description:  'Private registry credentials',
  id:           'registry',
  secret:       regPassword,
  userName:     regUsername
]

// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

// get credentials domain
def domain = Domain.global()

// get credentials store
def store = jenkins.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

// define registry secret
def jenkinsKeyUsernameWithPassword = new UsernamePasswordCredentialsImpl(
  CredentialsScope.GLOBAL,
  jenkinsKeyUsernameWithPasswordParameters.id,
  jenkinsKeyUsernameWithPasswordParameters.description,
  jenkinsKeyUsernameWithPasswordParameters.userName,
  jenkinsKeyUsernameWithPasswordParameters.secret
)

// add credential to store
store.addCredentials(domain, jenkinsKeyUsernameWithPassword)

// save to disk
jenkins.save()