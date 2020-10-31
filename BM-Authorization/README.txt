Aby wyeksportować Realm należy:
- mieć zamontowany volume (np. ./BM-Authorization/shared:/hostStorage)
- Dla Windows: wywołać poniższą komentę z CMD nie PS)

docker exec -it keycloak /opt/jboss/keycloak/bin/standalone.sh \
-Djboss.socket.binding.port-offset=100 \
-Dkeycloak.migration.action=export \
-Dkeycloak.migration.provider=singleFile \
-Dkeycloak.migration.realmName=business-manager \
-Dkeycloak.migration.usersExportStrategy=SKIP \
-Dkeycloak.migration.file=/hostStorage/realm.json
