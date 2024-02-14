[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=amzufar_advpro-module-2&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=amzufar_advpro-module-2)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=amzufar_advpro-module-2&metric=coverage)](https://sonarcloud.io/summary/new_code?id=amzufar_advpro-module-2)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=amzufar_advpro-module-2&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=amzufar_advpro-module-2)
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