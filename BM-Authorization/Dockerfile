FROM jboss/keycloak:9.0.3

ENV KEYCLOAK_REALM_INPUT=./base.json
ENV KEYCLOAK_REALM_OUTPUT=/tmp/base.json
ENV KEYCLOAK_IMPORT=${KEYCLOAK_REALM_OUTPUT}

ARG login=admin
ARG password=admin

RUN bash /opt/jboss/keycloak/bin/add-user-keycloak.sh -u ${login} -p ${password}

COPY ${KEYCLOAK_REALM_INPUT} ${KEYCLOAK_REALM_OUTPUT}

CMD ["-b","0.0.0.0","-Dkeycloak.profile=preview", "-Dkeycloak.profile.feature.token_exchange=enabled", "-Dkeycloak.profile.feature.admin_fine_grained_authz=enabled"]