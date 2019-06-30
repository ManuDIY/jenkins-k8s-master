# Jenkins on Kubernetes

This repository contains Jenkins master build `Dockerfile` and a sample manifest to deploy the same in Kubernetes.

To run a build Job , we need Jenkins . 
You can use the `Dockerfile` to build an initial version of the image and then use the same inside deployment yaml file.

Jenkins deployment need a `PersistentVolume` to store all job configurations and artifacts.
Its your responsibility to create a `PV` or adjust the StorageClassName if you have dynamic volume provisioner.

>TODO: Convert the Yaml file to helm chart