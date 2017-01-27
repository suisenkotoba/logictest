package suisen.logictest;

/**
 * Created by suisen on 17/07/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ScorECategory extends RecyclerView.Adapter<ScorECategory.ScoreViewHolder>{

    private List<HighScore.Score> scoreList;
    private Context context;

    public ScorECategory(List<HighScore.Score> scoreList, Context context) {
        this.scoreList = scoreList;
        this.context = context;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, null);
        ScoreViewHolder scoreViewHolder = new ScoreViewHolder(view);
        return scoreViewHolder;
    }
    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        HighScore.Score score = scoreList.get(position);

        holder.pic.setImageResource(score.getPicId());
        holder.scr.setText(String.valueOf(score.getScr()));
        holder.cat.setText(score.getCat());
    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        TextView scr;
        TextView cat;
        public ScoreViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.ivwinpic);
            scr = (TextView) itemView.findViewById(R.id.tvscore);
            cat = (TextView) itemView.findViewById(R.id.tvcategory);
        }
    }
}
