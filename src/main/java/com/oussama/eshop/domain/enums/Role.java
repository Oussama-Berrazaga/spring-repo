package com.oussama.eshop.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Set;

import static com.oussama.eshop.domain.enums.Permission.*;

@Getter
@AllArgsConstructor
public enum Role {
  CUSTOMER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_CREATE,
                  ADMIN_UPDATE,
                  ADMIN_READ,
                  ADMIN_DELETE,
                  MANAGER_CREATE,
                  MANAGER_UPDATE,
                  MANAGER_READ,
                  MANAGER_DELETE
          )),
  MANAGER(
          Set.of(
                  MANAGER_CREATE,
                  MANAGER_UPDATE,
                  MANAGER_READ,
                  MANAGER_DELETE
          ));

  private final Set<Permission> permissions;
}
