package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

    @Entity
    public class WashingAssistant implements Serializable {
        private static final long SerialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private String primaryLanguage;
        private int yearsOfExp;
        private int pricePrHour;

        public WashingAssistant() {
        }

        public WashingAssistant(String name, String primaryLanguage, int yearsOfExp, int pricePrHour) {
            this.name = name;
            this.primaryLanguage = primaryLanguage;
            this.yearsOfExp = yearsOfExp;
            this.pricePrHour = pricePrHour;
        }

        public static long getSerialVersionUID() {
            return SerialVersionUID;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrimaryLanguage() {
            return primaryLanguage;
        }

        public void setPrimaryLanguage(String primaryLanguage) {
            this.primaryLanguage = primaryLanguage;
        }

        public int getYearsOfExp() {
            return yearsOfExp;
        }

        public void setYearsOfExp(int yearsOfExp) {
            this.yearsOfExp = yearsOfExp;
        }

        public int getPricePrHour() {
            return pricePrHour;
        }

        public void setPricePrHour(int pricePrHour) {
            this.pricePrHour = pricePrHour;
        }

        @Override
        public String toString() {
            return "WashingAssistant{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", primaryLanguage='" + primaryLanguage + '\'' +
                    ", yearsOfExp=" + yearsOfExp +
                    ", pricePrHour=" + pricePrHour +
                    '}';
        }
    }

