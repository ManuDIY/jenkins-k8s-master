FROM jenkins/jenkins:2.164.3
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"
USER jenkins
ENV JENKINS_SLAVE_AGENT_PORT 50001
ADD init.groovy.d /usr/share/jenkins/ref/init.groovy.d
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
COPY passwd /etc/passwd
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt