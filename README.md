# Install docker

Install the OS addons to manage additional repos

```bash
sudo dnf -y install dnf-plugins-core
sudo dnf config-manager --add-repo https://download.docker.com/linux/fedora/docker-ce.repo
```

Add the docker repo to your local machine

```bash
sudo dnf install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

Start the docker daemon and check the installation:

```bash
sudo systemctl start docker
sudo docker run hello-world
```

# Database configuration

See the [Docker Compose](./compose.yml) file in order to see the DB configuration. You can start the pod with

```bash
sudo docker-compose up -d mysql
```

Then you need a DB manager. You can use anyone, but I will work with [DBeaver](https://dbeaver.com/) while developing
this project.

## Install DBeaver in fedora

```bash
curl -O https://dbeaver.io/files/24.2.0/dbeaver-ce-24.2.0-stable.x86_64.rpm
```

## URL string to connecto the the database:

The general formal to connet to the database is

```bash
<driver>:<db-kind>://<username>:<password>@<host>:<port>/<database>
```

So in our example would be translated to:

```bash
jdbc:mysql://quarkman:passwd@localhost:3306/quarkus-example?allowPublicKeyRetrieval=true&useSSL=false
```

> `allowPublicKeyRetrieval=true&useSSL=false` Disable the encryption for developing environment.