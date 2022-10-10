public class Logger {

    private String LOGFILE = "log.log";
    private DiskOperations diskOperations = new DiskOperations();

    public void writeToLog(String logItem) {
        diskOperations.createFileIfNotExists(LOGFILE);
        String text = diskOperations.readText(LOGFILE);
        diskOperations.writeToDisk(LOGFILE, logItem, text);
    }
}
