package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.RoleDTO;
import com.fred.schoolmanagement.dto.RoleSaveDTO;
import com.fred.schoolmanagement.dto.RoleUpdateDTO;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    String addRole(RoleSaveDTO roleSaveDTO);

    List<RoleDTO> getAllRoles();

    String updateRoles(long id, RoleUpdateDTO roleUpdateDTO);

    boolean deleteRole(long id);

    Optional<Entity> assignRole(Entity T, long roleCode);

}
