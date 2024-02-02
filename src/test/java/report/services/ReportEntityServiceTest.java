package report.services;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import report.models.Report;
import report.models.ReportEntity;
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


    @Test
    public void testRepo() {

        Report reportToBeSaved = new Report("Mario", 10, 1);
        Report expectedReport = new Report(1L, "Mario", 10, 1, 9);

        ReportEntity outputReport = new ReportEntity(1L, "Mario", 10, 1, 9);
        when(reportRepository.save(any(ReportEntity.class))).thenReturn(outputReport);

        Report savedReport = reportService.saveReport(reportToBeSaved);

        assertThat(savedReport).isEqualToComparingFieldByField(expectedReport);
        verify(reportRepository, times(1)).save(any(ReportEntity.class));
    }
}
