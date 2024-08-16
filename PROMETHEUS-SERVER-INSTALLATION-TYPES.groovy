


1.'Using Pre-Compiled Binaries:'

Precompiled Binaries are available for most Prometheus Components, 
which can be downloaded from the official site.
https://prometheus.io/download/

2.'From Source:'
Prometheus Components can be built from Source Using the Makefile targets in the 
respective repositories.

3.'Using Docker:'
Prometheus services are available as Docker Images, which can be easily run with a 
docker run command. It is recommended to use a named volume for 'data persistence'.
Can Be Found:    'Quay.io or Docker Hub.'

A.Setting Command Line Parameters:
Default command line parameters are set in the Dockerfile. 
Custom Parameters can be added when running the container.

B.Volumes & Bind-Mount:
Custom configurations can be provided by bind-mounting the 'prometheus.yml' file or 
a directory containing it to the container.

Volumes & bind-mount
To provide your own configuration, there are several options. Here are two examples.

Bind-Mount your 'prometheus.yml' from the host by running:

docker run \
    -p 9090:9090 \
    -v /path/to/prometheus.yml:/etc/prometheus/prometheus.yml \
    prom/prometheus

Or Bind-Mount the directory containing 'prometheus.yml' onto '/etc/prometheus' by running:

docker run \
    -p 9090:9090 \
    -v /path/to/config:/etc/prometheus \
    prom/prometheus

C.Saving Prometheus Data:
To 'persist data across container restarts, set up a persistent storage volume.'

Run Prometheus container with persistent storage:

# Create persistent volume for your data
docker volume create prometheus-data
# Start Prometheus container
docker run \
    -p 9090:9090 \
    -v /path/to/prometheus.yml:/etc/prometheus/prometheus.yml \
    -v prometheus-data:/prometheus \
    prom/prometheus


D.Custom Image:
Custom Images with baked-in configurations can be created by building a Dockerfile
 with the necessary settings.

4.'Using Configuration Management Systems:'

Prometheus can be managed using various configuration management systems, 
such as:

 Ansible: prometheus-community/ansible
  https://github.com/prometheus-community/ansible)

 Chef: rayrod2030/chef-prometheus
 https://github.com/elijah/chef-prometheus

 Puppet:puppet/prometheus
 https://forge.puppet.com/modules/puppet/prometheus/readme

 SaltStack: saltstack-formulas/prometheus-formula
 https://github.com/saltstack-formulas/prometheus-formula

through community-contributed resources.