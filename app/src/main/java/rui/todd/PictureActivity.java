package rui.todd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

public class PictureActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        iv = findViewById(R.id.picture);
//        Bundle extras = getIntent().getExtras();
        String url = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAYAAADimHc4AAAFjUlEQVR4Xu2dh04jMRCGHXoViN6RkOD9XweBkBAdRO81py/CkbPZks3aDPjGEjq4s3ec//d0L1er1 t1o0MMgZoSIIZ9Q7ASIIu/EiCMvxKgBEgjICxffYASIIyAsHjVACVAGAFh8aoBSoAwAsLiVQOUAGEEhMWrBigBwggIi1cNUAKEERAWrxqgBAgjICxeNUAJEEZAWLxqgBIgjICweNWA/5UA7oO9vb2Z19dXw/eDg4ONr1qtFhySj48P8/7 3pA7MDBg vr6gsvMEiCmAQ8PD b8/Nx8fn42gOjt7TULCwtmdHQ0KBgvLy/m9PTUQAIDufPz82ZkZCSo3F9FAIDv7e01QbCbGxoaMuvr60GB2N/fN5Dgjv7 frOxsRFU7q8igFO/u7ubuqetra2gZmh7e1tE7q8i4OnpyRwcHKTuaXV1NZg5 Pr6Mjs7O21y8Tubm5tBif8xAqxzvbu7MwCNs3MH/w4QWaOnp8dgikI4Y5x cj92H5ghggCGvTDOHnDSw8PDjT2FcNZenTDAXl9fm5ubmzb7LmJgPQqFgKmpKTM Pu7xqZ5vR9/e3jYim7wT7nX3P/wwNMB3pOZNAwD97OzMYHpiHpOTk2Zubs6bifRGAJEN8TXxfcxjbGzMLC4uGnyVj6EElEQRAjBDJHA hhJQEkUloCRgvqcrAb4RLfk8JaAkYL6nKwG ES35vD9FAKn87OxskBS JG5dTackQVb//PzcXP nCPiJ8nJXyJZYBAEkmHYoASXA8zGV8goJZnQEUJnERFGF/M0jOgKoGR0fH5vHx8cW3N3yMyVgsk1MmB3YYyqtV1dXmcU ygMzMzNmYmKipVZzf3/fbH mkY1sajysTZbBoyOAk08zxvZks04/IEKCHdSaTk5O2ohLroe05eXlFscP4ZCQN9BEmkFJjYyOAG5BHB4eFhKAswNIlwCApMmTN9CelZWVFiCPjo4Ki4QAzzrWu0MJ EYDDVACco5eWjk6LQxVDWgFMWg1NI0AerL4gKzerN1etz6Avi6mxO3f4juKGkWYoLW1tbakMToTBMAkNzRusn5bGrX26enpZpOcNcwFDNZmtTxtNENE40YzaB3JVNLxM4cvyILwtH5vlARUifsBP4sAwtCsTpW9jeGSDvjMz7uBoQRUYcvDWiXAA4hVHqEEVEHPw9poCcCOZznhPLucd7OuyJ6z1jripK/IarJHRwDAExbmXV8BjKWlpbY7ooBxcXHRuNKeNgCVOztENO6g/MGFMer6WQ6c64fITF4/jI4ASglktFkgVs0D0ur1lJMBMm/kkR5VOZqTzwcqIoCYnBNZthbEixasc01KJ7Ug5nPhKvmCSHQa0CkB3RbjlADnamJaKUIJEK4F8XoQJqGoH0A5gXe37OjEeTMX00UfwY1yOqkFYYIof OMoy5H8 HobFEcy4pIKIwRzSRr8zjwy8vLTPKIYKghJV 4Y519ITDNEVOKAHgIT4an0fkAF4CsPKDo7Zhu1uH07RuZSRIAPas3HTUBHhLV4I9QAoJDnC9ACVACOkMgrSWJU QF6CKb3pkEmVl/5mYcEQ0ZbvL6h/0dEDLwVZOKs6d 5IbMaWFuFSneesJslhCRi1Ox/sdMaDKvqhLq tJqbwRwCmi0owVFd3eqnBjJteQlJGvJ/KTKnrwSwEY4/WiBTbRCa0OV5yfXpj2Lk06WTKLGVXvfd1m9E BWL6nD5zVeqpycThK65PNdgPne/dlm5fbv ZMEjUCCEx/q1ycEI8AXuFLPgQBfdj7vMygBUgx/y1UClABhBITFqwYoAcIICItXDVAChBEQFq8aoAQIIyAsXjVACRBGQFi8aoASIIyAsHjVACVAGAFh8aoBSoAwAsLiVQOUAGEEhMWrBigBwggIi/8HMH7tLO8SqhoAAAAASUVORK5CYII=";
        int flag = Base64.DEFAULT;
        byte[] b = Base64.decode(url.split(",")[1], flag);
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        iv.setImageBitmap(bmp);
    }
}
