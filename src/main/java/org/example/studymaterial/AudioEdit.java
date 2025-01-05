package org.example.studymaterial;

import org.example.studymaterial.AudioReference.AudioQuality;

public record AudioEdit(
        String title,
        String description,
        String link,
        AudioQuality audioQuality,
        boolean downloadable,
        String accessRights,
        String license,
        String language,
        int rating,
        int viewCount,
        int shareCount
) {

    public static AudioEditBuilder builder() {
        return new AudioEditBuilder();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public boolean isDownloadable() {
        return downloadable;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public String getLicense() {
        return license;
    }

    public String getLanguage() {
        return language;
    }

    public int getRating() {
        return rating;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    // Método para verificar se o áudio é de alta qualidade
    public boolean isHighQuality() {
        return audioQuality == AudioQuality.HIGH || audioQuality == AudioQuality.VERY_HIGH;
    }

    // Método para obter uma representação textual do objeto
    public String toDetailedString() {
        return "Título: " + title + 
               "\nDescrição: " + description +
               "\nLink: " + link + 
               "\nQualidade: " + audioQuality + 
               "\nDownloadável: " + downloadable +
               "\nDireitos de Acesso: " + accessRights +
               "\nLicença: " + license +
               "\nIdioma: " + language +
               "\nAvaliação: " + rating +
               "\nVisualizações: " + viewCount +
               "\nCompartilhamentos: " + shareCount;
    }

    // Método para verificar se o áudio tem direitos de acesso restritos
    public boolean hasRestrictedAccess() {
        return !accessRights.equals("Público"); 
    }

    // Método para verificar se o áudio é licenciado sob Creative Commons
    public boolean isCreativeCommons() {
        return license.startsWith("CC "); 
    }

    // Método para calcular a média entre visualizações e compartilhamentos
    public double getAverageViewsAndShares() {
        return (double) (viewCount + shareCount) / 2;
    }


    public static class AudioEditBuilder {
        private String title;
        private String description;
        private String link;
        private AudioQuality audioQuality;
        private boolean downloadable;
        private String accessRights;
        private String license;
        private String language;
        private int rating;
        private int viewCount;
        private int shareCount;

        public AudioEditBuilder title(String title) {
            this.title = title;
            return this;
        }

        public AudioEditBuilder description(String description) {
            this.description = description;
            return this;
        }

        // Similar builder methods for other attributes
        public AudioEditBuilder link(String link) {
            this.link = link;
            return this;
        }

        public AudioEditBuilder audioQuality(AudioQuality audioQuality) {
            this.audioQuality = audioQuality;
            return this;
        }

        public AudioEditBuilder isDownloadable(boolean downloadable) {
            this.downloadable = downloadable;
            return this;
        }

        public AudioEditBuilder accessRights(String accessRights) {
            this.accessRights = accessRights;
            return this;
        }

        public AudioEditBuilder license(String license) {
            this.license = license;
            return this;
        }

        public AudioEditBuilder language(String language) {
            this.language = language;
            return this;
        }

        public AudioEditBuilder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public AudioEditBuilder viewCount(int viewCount) {
            this.viewCount = viewCount;
            return this;
        }

        public AudioEditBuilder shareCount(int shareCount) {
            this.shareCount = shareCount;
            return this;
        }

        public AudioEdit build() {
            return new AudioEdit(title, description, link, audioQuality, downloadable, accessRights, license, language, rating, viewCount, shareCount);
        }
    }
}