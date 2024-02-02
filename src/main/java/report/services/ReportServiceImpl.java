package report.services;

import report.models.Report;
import report.models.ReportEntity;
import report.repositories.ReportRepository;
import report.utils.ReportUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportUtils reportUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Report saveReport(Report report) {

        ReportEntity reportEntity = modelMapper.map(report, ReportEntity.class);

        reportEntity = reportRepository.save(reportEntity);

        Report savedReport = modelMapper.map(reportEntity, Report.class);

        return savedReport;
    }

    @Override
    public Report getReportById(Long id) {
        try {
            ReportEntity reportEntity = reportRepository.getOne(id);
            return modelMapper.map(reportEntity, Report.class);
        } catch (EntityNotFoundException ex) {
            // Handle entity not found exception
            return null; // Or throw your own custom exception
        }
    }

    @Override
    public List<Report> getAllReports() {
        return null;
    }

    @Override
    public void deleteReportById(Long id) {

        try {
            reportRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

            throw new EntityNotFoundException("Couldn't resolve ID: " + id);
        }

    }
}
