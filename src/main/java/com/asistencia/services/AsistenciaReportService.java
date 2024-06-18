package com.asistencia.services;

import com.asistencia.models.Asistencia;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface AsistenciaReportService {
    List<Asistencia> findAll();
    Asistencia findById(Long id);
    Asistencia save(Asistencia asistencia);
    void deleteById(Long id);
    byte[] exportPdf() throws JRException, FileNotFoundException;
    byte[] exportXls() throws JRException, FileNotFoundException;
}

