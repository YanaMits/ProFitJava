package ProFit.web.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType. IDENTITY)
        private Integer id;

        @Column(nullable = false, unique = true, length = 45)
        private String email;

        @Column (length = 25)
        private String name;

        @Column (length = 15)
        private int password;

        @Column (length = 15)
        private String phone;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getPassword() {
                return password;
        }

        public void setPassword(int password) {
                this.password = password;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }
}