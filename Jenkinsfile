def label = "kaniko-${UUID.randomUUID().toString()}"

podTemplate(name: 'kaniko', cloud: 'Kubernetes', label: label, yaml: """
kind: Pod
metadata:
  name: kaniko
spec:
  containers:
  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug-539ddefcae3fd6b411a95982a830d987f4214251
    imagePullPolicy: Always
    command:
    - /busybox/cat
    tty: true
    volumeMounts:
      - name: jenkins-docker-cfg
        mountPath: /root
      - name: ca-cert
        mountPath: /kaniko/ssl/certs/my-ca.pem
        subPath: rootca
  volumes:
  - name: ca-cert
    configMap:
      name: registry-ca
      items:
      - key: root-ca
        path: rootca
  - name: jenkins-docker-cfg
    projected:
      sources:
      - secret:
          name: local-registry
          items:
            - key: .dockerconfigjson
              path: .docker/config.json
"""
  ) {

  node(label) {
    stage('Build with Kaniko') {
      git 'https://github.com/ansilh/jenkins-k8s-master.git'
      container(name: 'kaniko', shell: '/busybox/sh') {
        withEnv(['PATH+EXTRA=/busybox:/kaniko']) {
          sh '''#!/busybox/sh
          /kaniko/executor -f `pwd`/Dockerfile -c `pwd` --cache=true --destination=10.128.0.4:5000/jenkins-k8s-master
          '''
        }
      }
    }
  }
}