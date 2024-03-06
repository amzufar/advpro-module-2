[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=amzufar_advpro-module-2&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=amzufar_advpro-module-2)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=amzufar_advpro-module-2&metric=coverage)](https://sonarcloud.io/summary/new_code?id=amzufar_advpro-module-2)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=amzufar_advpro-module-2&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=amzufar_advpro-module-2)
# Tutorial 4
Pada tutorial ini, saya belajar TDD (Test Driven Development).
## Refleksi
1. Menurut saya, flow TDD ini sangat bermanfaat. Kita sebagai developer diminta untuk membuat test terlebih dahulu, sebelum melakukan implementasi feature. Hal tersebut dapat mengurangi jumlah bugs/error pada hasil kerja/code kita.
2. FIRST Principle:
   * Fast: Test yang saya buat pada Tutorial ini berjalan dengan cepat dan tidak mengganggu workflow lainnya.
   * Isolated/Independent: Sebelum menjalankan test, saya sudah membuat function `setUp()` yang nantinya akan berjalan untuk setiap kali melakukan test dengan bantuan `@BeforeEach`. 
   * Repeatable: Test yang saya buat menghasilkan output yang konsisten.
   * Self-Validating: Menurut saya, test yang saya buat dapat dikatakan self-validating. Test tersebut sudah saya desain sedemikian rupa agar mudah dibaca dan dimengerti. 
   * Thorough/Timely: Pada tutorial ini, saya belum berhasil mencapai 100% Coverage pada unit test. Improvement untuk kedepannya yaitu menambah test di missed branch berdasarkan hasil report jacoco. 

# Tutorial 3
Pada tutorial ini, saya belajar mengenai penggunaan SOLID principles.

## Refleksi
1. Saya menerapkan semua SOLID principle secara menyeluruh:
   * Single Responsibility Principle (SRP): Each class should have a singular responsibility. Contohnya, pada class `CarController` mempunyai tugas sebagai controller object Car dan pada class `ProductController` mempunyai tugas sebagai controller untuk object Product.
   * Open-Closed Principle (OCP): A software artifact should be open for extension but closed for modification. Contohnya, saya membuat interface `ICarRepository` yang diimplementasikan pada class `CarRepository`. Apabila ada penambahan fitur yang diinginkan pada class `CarRepository` kita dapat dengan mudah membuat interface baru yang nantinya diimplementasikan juga pada kelas tersebut.
   * Liskov Substitution Principle (LSP): Derived or child classes must be substitutable for their base or parent classes. Contohnya, saya memisahkan `CarController` dari yang awalnya berada pada `ProductController`
   * Interface Segregation Principle (ISP): Interface segregation, emphasizing the subdivision of larger interfaces into smaller, more focused ones. Contohnya, saya memecah tiap function pada `CarService` menjadi beberapa interface yaitu `CarCreationService`, `CarRetrievalService`, `CarUpdateService`, dan `CarDeleteService`. Tujuannya adalah untuk menghindari interface yang terlalu rumit, mendorong pembentukan interface yang lebih kecil yang dapat disesuaikan dengan kebutuhan tugasnya.
   * Dependency Inversion Principle (DIP) : Emphasizes the use of abstraction, such as abstract classes and interfaces, over concrete implementations. Contohnya pada class `CarController` tidak boleh dependant pada class CarServices secara langsung, melainkan pada interfacenya.

2. Sudah saya jelaskan pada poin nomor 1.
3. Kerugian jika tidak menggunakan SOLID principle:
   * Software menjadi lebih kaku. Banyak class yang masih tightly coupled, sehingga software menjadi kurang fleksibel dan sulit di-maintain. Contohnya, pada `CarRepository` yang mengimplementasikan interface `ICarRepository`. Penambahan fitur pada class tersebut dapat dilakukan dengan mengimplementasikan interface baru sesuai dengan fitur tersebut.
   * Code sulit dimengerti/dipahami. Dengan menggunakan SOLID principle, otomatis kita akan menerapkan SRP yang menyatakan setiap class hanya diperbolehkan melakukan satu tugas saja. Jika kita tidak melakukan hal tersebut, class yang kita pakai akan berukuran besar dan cenderung lebih kompleks sehingga sulit untuk dipahami.

# Tutorial 2
Pada tutorial ini, saya belajar membuat workflow pada Github, yaitu meliputi CI, Scorecard, dan SonarCloud. Saya juga melakukan auto-deployment menggunakan koyeb.
## Refleksi
1. List of code quality issue(s) that I've fixed:
   *  Mengubah modifier pada class test, yang awalnya menggunakan `public` menjadi `default`.
   *  Define a constant instead of repeating myself. String value yang dipakai berulang-ulang saya letakkan pada sebuah constant static final.
   *  Field injection is not recommended. Object yang menggunakan injection `@Autowired` saya ubah dengan menggunakan initiation class.

2. Menurut saya, implementasi yang saya buat pada tutorial ini sudah memenuhi definisi dari CI/CD. Karena sudah ada workflow yang melakukan testing (`ci.yml`) dan pengecekan melalui SonarCloud (`sonarcloud-analysis.yml`) secara otomatis untuk setiap kali `push` ke repository. Setelah itu, apabila implementasi kode sudah cukup baik, saat melakukan pull request ke branch `master`, terdapat workflow yang mengecek Code Security (`scorecard.yml`) dan deployment (koyeb, dibantu oleh file `Dockerfile`) secara otomatis. Jadi, dapat disimpulkan implementasi pada tutorial ini sudah memenuhi definisi dari CI/CD yaitu membentuk otomasi workflow pada proses pengembangan sebuah software.

# Tutorial 1
Pada tutorial ini, saya belajar menggunakan Spring Boot. Pada tutorial kali ini juga saya menerapkan materi yang telah dipelajari di kelas yaitu `clean code` dan  `testing`.
## Refleksi 1

Setelah mengimplementasi dua _features_ tambahan (_edit_ dan _delete_), berikut beberapa _clean code principles_ yang sudah saya gunakan di tutorial ini:

1. Meaningful names: 
   * Penggunaan nama yang jelas untuk masing-masing function/method. 
   * Contoh: `editProductPage`, `editProductPost`, `findById`, dll.
2. Small function:
   * Masing-masing function hanya melakukan 1 task.
   * Contoh: `update` dan `delete` pada class ProductRepository.
3. Brief Comment to describe what the code does:
   * Contoh: pada class ProductServiceImpl saya menambahkan comment untuk menjelaskan 1 line code.

Namun, saya belum dapat melakukan _error handling_ dengan baik. Pada class ProductRepository, terdapat fungsi `findById` dan `update` yang masih me-_return_ nilai `null` pada salah satu case-nya.

## Refleksi 2
1. Jawaban untuk pertanyaan nomor 1:
   * Setelah membuat/menulis unit test, saya merasa lebih yakin bahwa code yang sudah saya implementasikan berjalan dengan baik dan benar. 
   * Menurut saya, salah satu cara untuk memastikan bahwa unit test sudah cukup untuk memverifikasi program adalah dengan melakukan unit testing untuk setiap method pada tiap class.
   * Tentu saja code coverage 100% tidak menjamin implementasi code kita bebas dari bug atau error. Untuk menjamin implementasi code bebas dari bug atau error, kita perlu memperhatikan _quality of test_ yang kita buat.