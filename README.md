
# ChatApp

Proyek ini mengimplementasikan model komunikasi **client-server** menggunakan **Java Sockets** dengan protokol **TCP (Transmission Control Protocol)** untuk memastikan koneksi yang andal antara client dan server. Program server mendengarkan permintaan dari client pada port yang ditentukan, memproses pesan yang masuk, lalu mengirimkan respons kembali. Sementara itu, program client menghubungkan ke server melalui socket, mengirimkan pesan, dan menampilkan respons yang diterima dari server di konsol. Implementasi ini menunjukkan dasar komunikasi jaringan sederhana menggunakan Java untuk aplikasi client-server.
## Requirement

1. `Java 8` atau `yang lebih baru`

2. `IDE (misalnya, IntelliJ IDEA, Eclipse)` atau `terminal dengan Java SDK terpasang.`

3. `Java SDK 22` atau lakukan konfigurasi dengan SDK yang sudah terpasang pada bagian pom.xml 

```bash
    ...
    <properties>
        ...
        <maven.compiler.source>22</maven.compiler.source>
        <maven.compiler.target>22</maven.compiler.target>
    </properties>
    ...
```
## How to Run
1. Clone repositori ini ke komputer lokal Anda:
```bash
git clone https://github.com/username-anda/program-client-server.git
cd program-client-server
```
2. Pastikan Java sudah terpasang:
```bash
java -version
```
3. Jalankan program Server.java
4. Jalankan program CLient.java
5. Masukkan IP address dari pc/komputer yang menjalankan Server.java pada GUI yang tampil setelah menjalankan Client.Java
Jika menjalankan program pada pc/komputer yang sama maka masukkan **localhost** atau **127.0.0.1**
