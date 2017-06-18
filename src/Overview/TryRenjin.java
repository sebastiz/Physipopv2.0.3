package Overview;
import javax.script.*;
//... add additional imports here ...

public class TryRenjin {
public static void main(String[] args) throws Exception {
 // create a script engine manager:
 ScriptEngineManager manager = new ScriptEngineManager();
 // create a Renjin engine:
 ScriptEngine engine = manager.getEngineByName("Renjin");
 // check if the engine has loaded correctly:
 if(engine == null) {
     throw new RuntimeException("Renjin Script Engine not found on the classpath.");
 }

 engine.eval(new java.io.FileReader("import_example.R"));
}
}