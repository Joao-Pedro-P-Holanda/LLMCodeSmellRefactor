package org.example.studymaterial;

import java.util.List;
import java.util.Map;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }
    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality){
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality){
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    public void editAudio(AudioEdit edit) {
        editBasic(edit.getTitle(), edit.getDescription(), edit.getLink());
        this.setAccessRights(edit.getAccessRights());
        this.setLicense(edit.getLicense());
        this.setAudioQuality(edit.getAudioQuality());
        editVideoAttributes(edit.getRating(), edit.getLanguage(), edit.getViewCount(), edit.getShareCount(), edit.isDownloadable());
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        // Criar um objeto AudioEdit usando o construtor
        AudioEdit edit = new AudioEdit(
            properties.get(0), // title
            properties.get(1), // description
            properties.get(2), // link
            audioQuality,
            isDownloadable,
            properties.get(3), // accessRights
            properties.get(4), // license
            properties.get(5), // language
            intProperties.get(0), // rating
            intProperties.get(1), // viewCount
            intProperties.get(2)  // shareCount
        );

        // Chamar o método editAudio com o objeto AudioEdit criado
        this.editAudio(edit);
    }

    private void editVideoAttributes(int rating, String language, int viewCount, int shareCount,boolean isDownloadable){
        this.setRating(rating);
        this.setShareCount(shareCount);
        this.setViewCount(viewCount);
        this.setDownloadable(isDownloadable);
        this.setLanguage(language);
    }

    public void editBasic(String title, String description, String link){
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }

    @Override
    public void incrementCount(Map<String, Integer> referenceCount) {
        Integer audioCount = referenceCount.get("Audio References");
        referenceCount.put("Audio References", audioCount + 1);
    }
}
