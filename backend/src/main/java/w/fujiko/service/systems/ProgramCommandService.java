package w.fujiko.service.systems;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.model.masters.systems.ProgramCommand;

@Service
public interface ProgramCommandService extends UniversalCrud<ProgramCommand,Integer> {
    public List<ProgramCategory> getGrantedProgramCommandByUser(Integer userId);
}