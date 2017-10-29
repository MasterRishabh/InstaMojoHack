package insta.storage;

import java.io.IOException;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import entities.Line;
import entities.Pan;
import entities.PanDetail;
import entities.Region;
import entities.UserDetails;
import entities.Word;
import exception.ValidationException;
import hello.dataExtraction.DataExtractor;
import insta.dao.PanCardDao;
import object.PanStruct;
import util.ParseJson;
import verification.VerificationManager;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    
    @Autowired
    private PanCardDao panCardDao;
    
    @Autowired
    private DataExtractor dataExtractor;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file) throws ValidationException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            
            String result = dataExtractor.extractTextDetails(file.getInputStream());
            String colorValue = dataExtractor.extractImageDetails(file.getInputStream());
            System.out.println(colorValue);
            System.out.println(result);
            Pan panObject=ParseJson.parsonJson(result);
            PanStruct panStruct = setUpData(panObject, colorValue);
            panCardDao.savePancard(panStruct, file.getBytes());
            
            
            
            /*Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);*/
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
    }
    }
    
    public PanStruct setUpData(Pan pan, String colorValue) throws ValidationException {
    	String val="";
    	int i=0;
    	String[] values=new String[8];
    	for(Region region:pan.getRegions()) {
    		for(Line line:region.getLines()) {
    			for(Word word:line.getWords()) {
    				val+=word.getText()+" ";
    			}
    			values[i++]=val;

        		val="";
    		}
    	}
    	System.out.println(Arrays.toString(values));
    	PanStruct newPanStruct=new PanStruct(values[5],values[1],values[2],values[3],"26/04/2016", colorValue);
    	VerificationManager newManager=new VerificationManager(newPanStruct);
    	newManager.verify();
    	return newPanStruct;
    	
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

	@Override
	public List<UserDetails> leaderBoard() {
		return panCardDao.getLeaderBoard();
		
	}

	@Override
	public List<PanDetail> getAllUnverifiedPan() {
		// TODO Auto-generated method stub
		return panCardDao.getAllUnverifiedPan();
	}
}
