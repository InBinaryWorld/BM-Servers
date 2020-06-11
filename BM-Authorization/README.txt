Aby wyeksportować Realm należy:
- mieć zamontowany volume (np. ./BM-Authorization/shared:/hostStorage)
- wywołać poniższą komentę z CMD (ważne !!!!! CMD nie PS)
docker exec -it RUNNING_CONTAINER_ID /opt/jboss/keycloak/bin/standalone.sh -Djboss.socket.binding.port-offset=100 -Dkeycloak.migration.action=export -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.realmName=base -Dkeycloak.migration.usersExportStrategy=REALM_FILE -Dkeycloak.migration.file=/hostStorage/base.json