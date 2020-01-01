package com.sqlite.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqlite.controller.Client;

public interface ClientDao extends JpaRepository<Client, Integer> {


	List<Client> findAllByNameContaining(String name);


}
