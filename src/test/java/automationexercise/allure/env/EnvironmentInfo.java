package automationexercise.allure.env;

import lombok.experimental.UtilityClass;
import ru.slava62.automationexercise.util.ConfigUtils;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;

@UtilityClass
public class EnvironmentInfo {

  public static void setAllureEnvironment() {
      allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("URL", ConfigUtils.getBaseUrl())
                        .put("Stand", ConfigUtils.getStand())
                        .build());

  }
}
