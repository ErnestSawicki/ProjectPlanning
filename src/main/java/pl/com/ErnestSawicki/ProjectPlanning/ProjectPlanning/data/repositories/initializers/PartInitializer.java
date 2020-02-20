package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.initializers;

import lombok.extern.slf4j.Slf4j;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Part;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.PartRepository;

@Slf4j
public class PartInitializer {

    public void initializePartSampleData(PartRepository partRepository){
        createPartInDB(partRepository, "4213672014", "Cylinder housing");
        createPartInDB(partRepository, "8843720124", "Cylinder housing");
        createPartInDB(partRepository, "8843720100", "Volvo AMT - assembly");
        createPartInDB(partRepository, "4213367201", "Cover gear");
        createPartInDB(partRepository, "4213546871", "Piston gear");
        createPartInDB(partRepository, "1122465754", "Main shaft");
    }

    private void createPartInDB(PartRepository partRepository, String partNumber, String name){
        Part part = new Part();
        part.setPartNumber(partNumber);
        part.setName(name);
        partRepository.save(part);
    }


}
