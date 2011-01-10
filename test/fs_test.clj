(ns fs-test
  (:use [fs] :reload-all)
  (:use [clojure.test]))

(deftest listdir-test
  (is (not (empty? (listdir ".")))))

(deftest executable?-test
  (is (executable? ".")))

(deftest readable?-test
  (is (readable? ".")))

(deftest writeable?-test
  (is (writeable? ".")))

(deftest delete-test
  (let [f (tempfile)]
    (delete f)
    (is (not (exists? f)))))

(deftest exists?-test
  (is (exists? ".")))

; FIXME: This test sucks
(deftest abspath-test
  (is (> (count (abspath ".")) 2)))

; FIXME: This test sucks
(deftest normpath-test 
  (is (> (count (normpath ".")) 2)))

(deftest basename-test
  (is (= (basename "/a/b/c") "c")))

(deftest dirname-test
  (is (= (dirname "/a/b/c") "/a/b")))

(deftest directory?-test
  (is (directory? ".")))

(deftest file?-test
  (is (file? (tempfile))))

; FIXME: This test sucks
(deftest mtime-test
  (is (> (mtime (tempfile)) 0)))

(deftest size-test
  (let [f (tempfile)]
    (spit f "abc")
    (is (= (size f) 3))))

(deftest mkdir-test
  (let [f (tempfile)]
    (delete f)
    (mkdir f)
    (is (directory? f))))

(deftest mkdirs-test
  (let [f (tempfile)
        sub (join f "a" "b")]
    (delete f)
    (mkdirs sub)
    (is (directory? sub))))

(deftest join-test
  (is (= (join "a" "b" "c") "a/b/c")))

(deftest split-test
  (is (= (split "a/b/c") '("a" "b" "c"))))

(deftest rename-test
  (let [f (tempfile)
        new-f (str f "-new")]
    (rename f new-f)
    (is (not (exists? f)))
    (is (exists? new-f))))

; FIXME: Test all variations of tempfile
(deftest tempfile-test
  (is (file? (tempfile))))

; FIXME: Test all variations of tempdir
(deftest tempdir-test
  (is (directory? (tempdir))))

; FIXME: This test sucks
(deftest cwd-test
  (is (> (count (cwd)) 3)))