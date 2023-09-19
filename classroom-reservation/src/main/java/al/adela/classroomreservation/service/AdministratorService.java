package al.adela.classroomreservation.service;

import al.adela.classroomreservation.model.Administrator;
import al.adela.classroomreservation.model.Lecturer;
import al.adela.classroomreservation.repository.AdministratorRepository;
import al.adela.classroomreservation.repository.LecturerRepository;
import al.adela.classroomreservation.utils.ApiReturnObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdministratorService {

    private final AdministratorRepository administratorRepository;

    private final LecturerRepository lecturerRepository;

    @Autowired
    public AdministratorService (AdministratorRepository administratorRepository, LecturerRepository lecturerRepository){
        this.administratorRepository = administratorRepository;
        this.lecturerRepository = lecturerRepository;
    }

    public ApiReturnObject loginAdministrator(String username, String password) {
        log.info("Inside loginAdministrator");

        List<Administrator> allAdministrators = administratorRepository.findAll();
        ApiReturnObject returnObject = new ApiReturnObject();

        for (Administrator administrator : allAdministrators){
            if(administrator.getUsername().equals(username) && administrator.getPassword().equals(password)){
                returnObject.setStatus(true);
                returnObject.setDescription("Login successful");
                return returnObject;
            }
        }
        returnObject.setStatus(false);
        returnObject.setDescription("Login denied");
        return returnObject;
    }

    public Lecturer addLecturer(String username, String password) {

        log.info("Inside addLecturer");

        Lecturer lecturer = new Lecturer();
        lecturer.setUsername(username);
        lecturer.setPassword(password);

        Lecturer savedLecturer = lecturerRepository.save(lecturer);
        return savedLecturer;
    }
}
