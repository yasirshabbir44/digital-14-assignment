package service;

public interface Writer {


    void write(StringBuilder message);

    boolean close(String isClose);

}
