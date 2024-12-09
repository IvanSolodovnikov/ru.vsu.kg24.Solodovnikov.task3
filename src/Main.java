import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjWriter writer = new ObjWriter("model.obj");

        List<double[]> vertices = Arrays.asList(
                new double[]{0.0, 0.0, 0.0},
                new double[]{1.0, 0.0, 0.0},
                new double[]{1.0, 1.0, 0.0},
                new double[]{0.0, 1.0, 0.0},
                new double[]{0.5, 0.5, 1.0}
        );

        List<double[]> normals = Arrays.asList(
                new double[]{0.0, 0.0, 1.0}
        );

        List<double[]> texcoords = Arrays.asList(
                new double[]{0.0, 0.0},
                new double[]{1.0, 0.0},
                new double[]{1.0, 1.0},
                new double[]{0.0, 1.0}
        );

        List<int[]> faces = Arrays.asList(
                new int[]{0, 1, 2, 3},
                new int[]{0, 1, 4}
        );

        try {
            writer.write(vertices, normals, texcoords, faces);
            System.out.println("Модель успешно сохранена в model.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
