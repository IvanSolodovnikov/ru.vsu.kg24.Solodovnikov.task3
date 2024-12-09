import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ObjWriter {
    private String filename;

    public ObjWriter(String filename) {
        this.filename = filename;
    }

    public void write(List<double[]> vertices, List<double[]> normals, List<double[]> texcoords, List<int[]> faces) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Записываем вершины
            for (double[] vertex : vertices) {
                writer.write(String.format("v %.6f %.6f %.6f%n", vertex[0], vertex[1], vertex[2]));
            }

            // Записываем нормали, если они есть
            if (normals != null) {
                for (double[] normal : normals) {
                    writer.write(String.format("vn %.6f %.6f %.6f%n", normal[0], normal[1], normal[2]));
                }
            }

            // Записываем текстурные координаты, если они есть
            if (texcoords != null) {
                for (double[] texcoord : texcoords) {
                    writer.write(String.format("vt %.6f %.6f%n", texcoord[0], texcoord[1]));
                }
            }

            // Записываем полигоны
            if (faces != null) {
                for (int[] face : faces) {
                    StringBuilder faceLine = new StringBuilder("f ");
                    for (int index : face) {
                        faceLine.append(index + 1).append(" "); // Индексы в OBJ начинаются с 1
                    }
                    writer.write(faceLine.toString().trim() + System.lineSeparator());
                }
            }
        }
    }
}
