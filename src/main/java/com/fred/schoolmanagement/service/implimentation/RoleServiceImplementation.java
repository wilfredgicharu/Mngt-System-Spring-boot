package com.fred.schoolmanagement.service.implimentation;

import com.fred.schoolmanagement.dto.RoleDTO;
import com.fred.schoolmanagement.dto.RoleSaveDTO;
import com.fred.schoolmanagement.dto.RoleUpdateDTO;
import com.fred.schoolmanagement.entity.Role;
import com.fred.schoolmanagement.repository.RoleRepository;
import com.fred.schoolmanagement.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public String addRole(RoleSaveDTO roleSaveDTO) {
        Optional<Role> roleExists = roleRepository.findByRoleCode(roleSaveDTO.getRoleCode());

        if (roleExists.isPresent()){
            return ("Role with id" +roleSaveDTO.getRoleCode() + "Already Exists");
        }
        Role role = new Role(
                roleSaveDTO.getRoleCode(),
                roleSaveDTO.getRoleName()
        );

        roleRepository.save(role);
        return ("Role with ID"+ roleSaveDTO.getRoleCode() + "added successfully" );
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> getRole = roleRepository.findAll();
        List<RoleDTO> roleDTOList = new ArrayList<>();

        for (Role role: getRole){
            RoleDTO roleDTO = new RoleDTO(
                    role.getId(),
                    role.getRoleCode(),
                    role.getRoleName()
            );
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    @Override
    public String updateRoles(long roleCode, RoleUpdateDTO roleUpdateDTO) {
        if (roleRepository.existsById(roleCode)){
            Role role = roleRepository.getById(roleCode);
            role.setRoleCode(roleUpdateDTO.getRoleCode());
            role.setRoleName(roleUpdateDTO.getRoleName());

            roleRepository.save(role);
            System.out.println("\nRole details updated Successfully");
            return ("Role updated successful");
        } else {
            System.out.println("Role ID not Found");
            return "Role ID not found";
        }
    }

    @Override
    public boolean deleteRole(long id) {
       if (roleRepository.existsById(id)){
           roleRepository.deleteById(id);
           System.out.println("Role deleted successfully");
           return true;
       } else {
           System.out.println("Role could not be found");
           return false;
       }
    }

    @Override
    public Optional<Entity> assignRole(Entity T, long roleCode) {
        return Optional.empty();
    }
}
