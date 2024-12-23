package objwriter;

import math.Vector2f;
import math.Vector3f;
import model.Polygon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ObjWriter {

    public static StringBuilder writeVertices(ArrayList<Vector3f> vertices) throws IOException {
        StringBuilder sb = new StringBuilder();

        for (Vector3f vertex : vertices) {
            sb.append(String.format(Locale.US, "v %.4f %.4f %.4f%n", vertex.x, vertex.y, vertex.z));
        }

        return sb;
    }

    public static StringBuilder writeTextureVertices(ArrayList<Vector2f> textureVertices) {
        StringBuilder sb = new StringBuilder();

        for (Vector2f vertex : textureVertices) {
            sb.append(String.format(Locale.US, "vt %.4f %.4f%n", vertex.x, vertex.y));
        }

        return sb;
    }

    public static StringBuilder writeNormals(ArrayList<Vector3f> normals) {
        StringBuilder sb = new StringBuilder();

        for (Vector3f normal : normals) {
        sb.append(String.format(Locale.US, "vn %.4f %.4f %.4f%n", normal.x, normal.y, normal.z));
        }
        return sb;
    }

    protected static StringBuilder writePolygons(ArrayList<Polygon> polygons) {
        StringBuilder sb = new StringBuilder();
        for (Polygon polygon : polygons) {
            List<Integer> vertexIndices = polygon.getVertexIndices();
            List<Integer> textureIndices = polygon.getTextureVertexIndices();
            List<Integer> normalIndices = polygon.getNormalIndices();

            sb.append("f"); // Начало строки полигона

            if (!vertexIndices.isEmpty()) { // Если есть индексы вершин
                for(int i=0; i<vertexIndices.size(); i++) {
                    sb.append(" ");
                    sb.append(vertexIndices.get(i) + 1); // Добавляем индекс вершины (1-based)

                    if (!textureIndices.isEmpty() && i < textureIndices.size()) {
                        sb.append("/").append(textureIndices.get(i) + 1); // Добавляем индекс текстуры (если есть)
                    } else if (!normalIndices.isEmpty()){
                        sb.append("/");
                    }

                    if (!normalIndices.isEmpty() && i<normalIndices.size()) {
                        sb.append("/").append(normalIndices.get(i) + 1); // Добавляем индекс нормали (если есть)
                    } else {
                        sb.append("");
                    }
                }
                sb.append("\n"); // Конец строки полигона
            } else {
                // Обработка случая, когда нет вершин
                sb.append(" Error: No vertex indices provided for polygon\n");
            }
        }
        return sb;
    }

    /*protected static String write(Model model) {
        StringBuilder sb = new StringBuilder();
        for (Polygon polygon : model.polygons) {
            sb.append(writePolygons());
        }
        return sb.toString();
    }*/
}
