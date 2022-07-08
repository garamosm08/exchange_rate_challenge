package com.bcp.challenge.exchangerate.service;

import com.bcp.challenge.exchangerate.entity.RoleEntity;
import com.bcp.challenge.exchangerate.entity.UserEntity;
import com.bcp.challenge.exchangerate.model.RoleModel;
import com.bcp.challenge.exchangerate.model.UserModel;
import com.bcp.challenge.exchangerate.repository.RoleRepository;
import com.bcp.challenge.exchangerate.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository, RoleRepository roleRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel register(UserModel userModel){

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userModel, userEntity);

        Set<RoleEntity> roleEntities = new HashSet<>();
        for(RoleModel rm :userModel.getRoles()){
            Optional<RoleEntity> optRole = roleRepository.findById(rm.getId());
            if(optRole.isPresent()){
                roleEntities.add(optRole.get());
            }
        }
        userEntity.setRoles(roleEntities);
        userEntity.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        userEntity = userRepository.save(userEntity);

        BeanUtils.copyProperties(userEntity, userModel);

        Set<RoleModel> roleModels = new HashSet<>();
        RoleModel rm = null;
        for(RoleEntity re :userEntity.getRoles()){
            rm = new RoleModel();
            rm.setRoleName(re.getRoleName());
            rm.setId(re.getId());
            roleModels.add(rm);
        }
        userModel.setRoles(roleModels);
        return userModel;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(userName);

        if(userEntity == null){
            throw new UsernameNotFoundException("User does not exist!");
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userEntity, userModel);

        Set<RoleModel> roleModels = new HashSet<>();
        RoleModel rm = null;
        for(RoleEntity re :userEntity.getRoles()){
            rm = new RoleModel();
            rm.setRoleName(re.getRoleName());
            rm.setId(re.getId());
            roleModels.add(rm);
        }

        userModel.setRoles(roleModels);
        return userModel;
    }
}
