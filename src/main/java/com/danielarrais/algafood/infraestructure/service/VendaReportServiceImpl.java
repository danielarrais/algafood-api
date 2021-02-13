package com.danielarrais.algafood.infraestructure.service;

import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;
import com.danielarrais.algafood.domain.service.VendaConsultaService;
import com.danielarrais.algafood.domain.service.VendaReportService;
import com.danielarrais.algafood.infraestructure.exception.ReportException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class VendaReportServiceImpl implements VendaReportService {
    private final VendaConsultaService vendaConsultaService;

    public VendaReportServiceImpl(VendaConsultaService vendaConsultaService) {
        this.vendaConsultaService = vendaConsultaService;
    }

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filter) {
        try {
            var vendasDiarias = vendaConsultaService.consultarVendasDiarias(filter);
            var parametros = new HashMap<String, Object>() {{
                put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            }};
            var dtaSource = new JRBeanCollectionDataSource(vendasDiarias);

            var inputStream = this.getClass().getResourceAsStream("/reports/vendas_diarias.jasper");
            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dtaSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir o relatório de vendas diárias", e);
        }
    }
}
