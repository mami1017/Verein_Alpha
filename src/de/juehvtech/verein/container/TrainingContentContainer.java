package de.juehvtech.verein.container;

import android.os.Bundle;

@SuppressWarnings("serial")
public class TrainingContentContainer extends Container {
    private String content;
    private String note;

    public TrainingContentContainer(String details, String note) {
        super();
        this.content = details;
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String details) {
        this.content = details;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

//    @Override
//    public ContainerType getContainerType() {
//        return ContainerType.TRAINING_DETAILS;
//    }
//
//    @Override
//    public String getToastString() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Bundle toBundle() {
//        Bundle retval = new Bundle();
//        retval.putString("key", ContainerType.TRAINING_DETAILS.toString());
//        retval.putSerializable("content", this);
//        return retval;
//    }

    public static TrainingContentContainer fromBundle(Bundle bundle) {
        if (bundle == null)
            return null;
        TrainingContentContainer retval = null;
        if (bundle.getString("key").equals(
                ContainerType.TRAINING_DETAILS.toString())) {
            retval = (TrainingContentContainer) bundle
                    .getSerializable("content");
        }
        return retval;
    }

}
