package report.services;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import report.controllers.ReportController;
import report.models.Report;
import report.models.ReportEntity;
import report.models.ReportRequest;
import report.models.ReportResponse;
import report.repositories.ReportRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ReportEntityServiceTest {

    @MockBean
    ReportRepository reportRepository;

    @Autowired
    ReportService reportService;

    @Autowired
    ModelMapper modelMapper;


    /*
    @Test
    public void testRepo() {

        Report reportToBeSaved = new Report("Mario", 10, 1, 0);
        Report expectedReport = new Report(1L, "Mario", 10, 1, 0, 9);

        ReportEntity outputReport = new ReportEntity(1L, "Mario", 10, 1, 0, 9);
        when(reportRepository.save(any(ReportEntity.class))).thenReturn(outputReport);

        Report savedReport = reportService.saveReport(reportToBeSaved);

        assertThat(savedReport).isEqualToComparingFieldByField(expectedReport);
        verify(reportRepository, times(1)).save(any(ReportEntity.class));
    }

     */

    /*
    @Test
    public void testFurtherUpdate() {
        ReportEntity existingReport = new ReportEntity(1L, "Mario", 10, 1, 0, 9);
        Report request = new Report("Rossi", 8, 2, 0);
        Report expectedReport = new Report(1L, "Rossi", 8, 2, 0, 6);
        ReportEntity entityReport = new ReportEntity(1L, "Rossi", 8, 2, 0, 6);


        when(reportRepository.getOne(1L)).thenReturn(existingReport);
        when(reportRepository.save(any(ReportEntity.class))).thenReturn(entityReport);
        Report updatedReport = reportService.updateReport(request, 1L);

        assertThat(updatedReport).isEqualToComparingFieldByField(expectedReport);
    }

     */

}
