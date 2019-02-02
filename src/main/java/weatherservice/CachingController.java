package weatherservice;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class CachingController {

  private static final Logger logger = Logger.getLogger(CachingController.class.getName());

  @CacheEvict(cacheNames = "weatherData", allEntries = true)
  @RequestMapping(path = "/cache/clear", method = RequestMethod.POST, produces = "application/json")
  public void clearCaches() {
    logger.info("Clearing caches");
  }
}
