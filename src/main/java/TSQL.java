import Helpers.ParserHelper;
import Services.BootService;
import Services.Parser;
import Services.ServerService;

public class TSQL {
    public static void main(String[] args) {
        BootService bootService = new BootService(new ServerService(), new Parser(new ParserHelper()));
        bootService.start();
    }
}
