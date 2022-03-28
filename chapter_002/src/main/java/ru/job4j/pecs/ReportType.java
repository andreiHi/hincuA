package ru.job4j.pecs;

public enum ReportType {
    ABUSE_TO_ADMINISTRATION(120L, "abuse_to_administration"),
    FILTHY_LANGUAGE(48L, "filthy_language"),
    HEALTH_THREAT(72L, "health_threat"),
    VIOLATION_OF_THE_LAW(600L, "violation_of_the_law"),
    SPAM_AND_FAKES(336L, "spam_and_fakes"),
    INVALID_CONTENT(600L, "invalid_content"),
    VIRUS(null, "virus"),
    SIGNAL_COPY(null, "signal_copy"),
    FAKE_REPORT(null, "fake_report"),
    ACCOUNT_HACKING(null, "account_hacking"),
    DEFAULT(null, "default");

    private Long lockPeriod;
    private String name;

    ReportType(Long lockPeriod, String name) {
        this.lockPeriod = lockPeriod;
        this.name = name;
    }

    public Long getLockPeriod() {
        return lockPeriod;
    }

    public void setLockPeriod(Long lockPeriod) {
        this.lockPeriod = lockPeriod;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println(ReportType.FAKE_REPORT);
    }
}
