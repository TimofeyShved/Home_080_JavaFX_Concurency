package sample;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");

        Group root = new Group();
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        MyService myservice = new MyService(); // создание потока
        myservice.setOnSucceeded(new EventHandler<WorkerStateEvent>() { // когда заканчивается поток
            @Override
            public void handle(WorkerStateEvent event) { // получили данные
                System.out.println("Закончился поток: "+event.getSource().getValue()); // вывод на экран
            }
        });
        myservice.start(); // запускает поток
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static class MyService extends Service<String>{ // мой поток со строкой
        @Override
        protected Task<String> createTask() { // выполенние кода
            return new Task<String>() { // запускаем новый таск
                @Override
                protected String call() throws Exception { //возвращаем строку
                    return "(」°ロ°)」 ДА!";
                }
            };
        }
    }
}
