package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.protobuf.TextFormat.Printer;

public interface PrinterJPA extends JpaRepository<Printer, String> {

}
