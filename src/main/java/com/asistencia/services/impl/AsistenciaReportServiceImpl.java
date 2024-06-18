package com.asistencia.services.impl;

import com.asistencia.models.Asistencia;
import com.asistencia.repositories.AsistenciaReportRepository;
import com.asistencia.services.AsistenciaReportService;
import com.asistencia.util.AsistenciaReport;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
@Service
public class AsistenciaReportServiceImpl implements AsistenciaReportService {
    @Autowired
    private AsistenciaReportRepository asistenciaReportRepository;

    @Autowired
    private AsistenciaReport asistenciaReport;

    @Override
    public List<Asistencia> findAll() {
        return asistenciaReportRepository.findAll();
    }

    @Override
    public Asistencia findById(Long id) {
        return asistenciaReportRepository.findById(id).get();
    }

    @Override
    public Asistencia save(Asistencia pet) {
        return asistenciaReportRepository.save(pet);
    }

    @Override
    public void deleteById(Long id) {
        asistenciaReportRepository.deleteById(id);
    }

    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return asistenciaReport.exportToPdf(asistenciaReportRepository.findAll());
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        return asistenciaReport.exportToXls(asistenciaReportRepository.findAll());
    }

}
