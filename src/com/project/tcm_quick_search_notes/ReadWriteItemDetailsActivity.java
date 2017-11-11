/*
 * Copyright (c) 2017, Wen Xiongchang <udc577 at 126 dot com>
 * All rights reserved.
 *
 * This software is provided 'as-is', without any express or implied
 * warranty. In no event will the authors be held liable for any
 * damages arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any
 * purpose, including commercial applications, and to alter it and
 * redistribute it freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must
 * not claim that you wrote the original software. If you use this
 * software in a product, an acknowledgment in the product documentation
 * would be appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such, and
 * must not be misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source
 * distribution.
 */

// NOTE: The original author also uses (short/code) names listed below,
//       for convenience or for a certain purpose, at different places:
//       wenxiongchang, wxc, Damon Wen, udc577

package com.project.tcm_quick_search_notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android_assistant.Hint;

public class ReadWriteItemDetailsActivity extends Activity {
	
	private static final String[] MEDICINE_COLUMNS = {
		"name",
		"alias",
		"category",
		"nature",
		"tastes",
		"channel_tropism",
		"relations_with_life_fundamentals",
		"motion_form_of_action",
		"effects",
		"actions_and_indications",
		"details",
		"common_prescriptions",
		"common_partners",
		"similar_medicines",
		"dosage_reference",
		"contraindications",
		"reference_material",
		"remarks"
	};
	
	private static final int MEDICINE_COLUMN_INDEX_NAME = 0;
	private static final int MEDICINE_COLUMN_INDEX_ALIASES = 1;
	private static final int MEDICINE_COLUMN_INDEX_CATEGORY = 2;
	private static final int MEDICINE_COLUMN_INDEX_NATURE = 3;
	private static final int MEDICINE_COLUMN_INDEX_TASTES = 4;
	private static final int MEDICINE_COLUMN_INDEX_CHANNEL_TROPISM = 5;
	private static final int MEDICINE_COLUMN_INDEX_LIFE_FUNDAMENTALS = 6;
	private static final int MEDICINE_COLUMN_INDEX_MOTION_FORMS_OF_ACTION = 7;
	private static final int MEDICINE_COLUMN_INDEX_EFFECTS = 8;
	private static final int MEDICINE_COLUMN_INDEX_ACTIONS_AND_INDICATIONS = 9;
	private static final int MEDICINE_COLUMN_INDEX_DETAILS = 10;
	private static final int MEDICINE_COLUMN_INDEX_COMMON_PRESCRIPTIONS = 11;
	private static final int MEDICINE_COLUMN_INDEX_COMMON_PARTNERS = 12;
	private static final int MEDICINE_COLUMN_INDEX_SIMILAR_MEDICINES = 13;
	private static final int MEDICINE_COLUMN_INDEX_DOSAGE_REFERENCE = 14;
	private static final int MEDICINE_COLUMN_INDEX_CONTRAINDICATIONS = 15;
	private static final int MEDICINE_COLUMN_INDEX_REFERENCE_MATERIAL = 16;
	private static final int MEDICINE_COLUMN_INDEX_REMARKS = 17;
	
	private DetailContentTemplate[] mDetailContentTemplates = {
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate(),
		new DetailContentTemplate()
	};
	
	private HashMap<String, DetailContentTemplate> mMapDetailContentTemplates = new HashMap<String, DetailContentTemplate>();
	
	private static final String[] DETAIL_CONTENT_FIELDS = {
		"checkBoxFlags",
		"spnKey",
		"etxKey",
		"spnValuePrefix_1",
		"etxValuePrefix_1",
		"spnValuePrefix_2",
		"etxValuePrefix_2",
		"spnValue",
		"etxValueShort",
		"spnValueSuffix_1",
		"etxValueSuffix_1",
		"spnValueSuffix_2",
		"etxValueSuffix_2",
		"etxValueLong"
	};
	
	private static final int ITEM_COLUMN_INDEX_CHKBOX = 0;
	private static final int ITEM_COLUMN_INDEX_SPN_KEY = 1;
	private static final int ITEM_COLUMN_INDEX_ETX_KEY = 2;
	private static final int ITEM_COLUMN_INDEX_SPN_VALUE_PREFIX_1 = 3;
	private static final int ITEM_COLUMN_INDEX_ETX_VALUE_PREFIX_1 = 4;
	private static final int ITEM_COLUMN_INDEX_SPN_VALUE_PREFIX_2 = 5;
	private static final int ITEM_COLUMN_INDEX_ETX_VALUE_PREFIX_2 = 6;
	private static final int ITEM_COLUMN_INDEX_SPN_VALUE = 7;
	private static final int ITEM_COLUMN_INDEX_ETX_VALUE_SHORT = 8;
	private static final int ITEM_COLUMN_INDEX_SPN_VALUE_SUFFIX_1 = 9;
	private static final int ITEM_COLUMN_INDEX_ETX_VALUE_SUFFIX_1 = 10;
	private static final int ITEM_COLUMN_INDEX_SPN_VALUE_SUFFIX_2 = 11;
	private static final int ITEM_COLUMN_INDEX_ETX_VALUE_SUFFIX_2 = 12;
	private static final int ITEM_COLUMN_INDEX_ETX_VALUE_LONG = 13;
	
	private static final String[] DETAIL_CONTENT_SPINNER_NAMES = {
		"spnKey",
		"spnValuePrefix_1",
		"spnValuePrefix_2",
		"spnValue",
		"spnValueSuffix_1",
		"spnValueSuffix_2"
	};
	
	private static final String[] DETAIL_CONTENT_EDIT_TEXT_NAMES = {
		"etxKey",
		"etxValuePrefix_1",
		"etxValuePrefix_2",
		"etxValueShort",
		"etxValueSuffix_1",
		"etxValueSuffix_2",
		"etxValueLong"
	};
	
	private static final int CHKBOX_VISIBLE = 1;
	private static final int CHKBOX_CLICKABLE = 2;
	private static final int CHKBOX_SELECTED = 4;
	
	private static final String ITEM_READ_ONLY = "read-only";
	private static final String SPACE = " ";
	
	private Menu gMenu = null;
	
	private DbHelper mDbHelper = null;
	private HashMap<String, String> mDetailContentFromDb = null;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_write_item_details);
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.default_action_bar_style));
		
		Intent intent = getIntent();
		int opType = intent.getIntExtra("op_type", QueryEntryActivity.OP_TYPE_MEDICINE);
		String primaryId = intent.getStringExtra("id");
		//String name = intent.getStringExtra("name");
		
		setTitle(getString(R.string.main_item_medicine));
		Hint.longToast(this, primaryId);
		
		mDbHelper = new DbHelper(this);
		mDbHelper.openOrCreate();
		
		mDetailContentFromDb = mDbHelper.queryMedicineDetails(primaryId);
		
		if (null == mDetailContentFromDb) {
			Hint.alert(this, getString(R.string.db_error),
				getString(R.string.not_found) + ": " + "id = " + primaryId,
				new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							ReadWriteItemDetailsActivity.this.finish();
						}
				});
		}
		
		// fillDetailTitleTemplates();
		fillDetailContentTemplates();
		
		fillDetailTitles();
		fillDetailContents();
	}
	
	@Override
	public void onDestroy() {
		if (null != mDbHelper)
			mDbHelper.close();
		
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_write_item_details, menu);
		gMenu = menu;
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		int[][] detailResIds = getDetailItemResourceIds();

		if (R.id.menu_edit == id) {
			item.setVisible(false);
			gMenu.findItem(R.id.menu_save).setVisible(true);
			gMenu.findItem(R.id.menu_cancel).setVisible(true);
			
			for (int i = 0; i < detailResIds.length; ++i) {
				if (detailContentIsNotUsed(i))
					continue;
				
				ListView lsvContents = (ListView) findViewById(detailResIds[i][2]);
				DetailContentAdapter contentsAdapter = (DetailContentAdapter) lsvContents.getAdapter();
				DetailContentTemplate template = mDetailContentTemplates[i];
				
				for (int j = 0; j < contentsAdapter.getCount(); ++j) {
					DetailContentData contentData = (DetailContentData) contentsAdapter.getItem(j);
					
					if (0 != (template.checkBoxFlags & CHKBOX_VISIBLE)) {
						contentData.checkBoxFlags |= CHKBOX_CLICKABLE;
					}
					
					contentData.spinnersEnabled = true;
					contentData.editTextsEnabled = true;
				}
				contentsAdapter.notifyDataSetChanged();
			}
			
			return true;
		}
		else if (R.id.menu_save == id) {
			item.setVisible(false);
			gMenu.findItem(R.id.menu_cancel).setVisible(false);
			gMenu.findItem(R.id.menu_edit).setVisible(true);
			
			ArrayList<String> updateArgs = new ArrayList<String>();
			View convertView = null;
			DetailContentAdapter.ViewHolder viewHolder = null;
			
			for (int i = 0; i < detailResIds.length; ++i) {
				if (detailContentIsNotUsed(i))
					continue;
				
				ListView lsvContents = (ListView) findViewById(detailResIds[i][2]);
				DetailContentAdapter contentsAdapter = (DetailContentAdapter) lsvContents.getAdapter();
				DetailContentTemplate template = mDetailContentTemplates[i];
				
				if (MEDICINE_COLUMN_INDEX_REFERENCE_MATERIAL == i || detailContentIsSimple(i)) {
					convertView = contentsAdapter.getView(0, null/*convertView*/, null);
					viewHolder = (DetailContentAdapter.ViewHolder) convertView.getTag();
					updateArgs.add(viewHolder.etxValueLong.getText().toString());
				}
				else if (MEDICINE_COLUMN_INDEX_CATEGORY == i) {
					convertView = contentsAdapter.getView(0, null, null);
					viewHolder = (DetailContentAdapter.ViewHolder) convertView.getTag();
					updateArgs.add(String.valueOf(viewHolder.spnValue.getSelectedItemPosition()));
				}
				else {
					String fieldValue = serializeToDatabaseField(contentsAdapter.getItemList());
					
					updateArgs.add(fieldValue);
				}
				
				for (int j = 0; j < contentsAdapter.getCount(); ++j) {
					DetailContentData contentData = (DetailContentData) contentsAdapter.getItem(j);
					
					if (0 != (template.checkBoxFlags & CHKBOX_VISIBLE)) {
						contentData.checkBoxFlags &= (~CHKBOX_CLICKABLE);
					}
					
					contentData.spinnersEnabled = false;
					contentData.editTextsEnabled = false;
				}
				contentsAdapter.notifyDataSetChanged();
			}
			
			updateArgs.add(getIntent().getStringExtra("id"));
			
			mDbHelper.updateMedicineItem(updateArgs.toArray(new String[updateArgs.size()]));
			
			this.finish();
		}
		else if (R.id.menu_cancel == id) {
			item.setVisible(false);
			gMenu.findItem(R.id.menu_save).setVisible(false);
			gMenu.findItem(R.id.menu_edit).setVisible(true);
			
			for (int i = 0; i < detailResIds.length; ++i) {
				if (detailContentIsNotUsed(i))
					continue;
				
				ListView lsvContents = (ListView) findViewById(detailResIds[i][2]);
				DetailContentAdapter contentsAdapter = (DetailContentAdapter) lsvContents.getAdapter();
				DetailContentTemplate template = mDetailContentTemplates[i];
				
				for (int j = 0; j < contentsAdapter.getCount(); ++j) {
					DetailContentData contentData = (DetailContentData) contentsAdapter.getItem(j);
					
					if (0 != (template.checkBoxFlags & CHKBOX_VISIBLE)) {
						contentData.checkBoxFlags &= (~CHKBOX_CLICKABLE);
						/*if (0 != (contentData.checkBoxFlags & CHKBOX_SELECTED))
							Hint.alert(this, MEDICINE_COLUMNS[contentData.medicineColumnIndex], String.valueOf(j));*/
					}
					
					contentData.spinnersEnabled = false;
					contentData.editTextsEnabled = false;
				}
				contentsAdapter.notifyDataSetChanged();
			}
			
			// TODO: ...
		}
		else
			;
		
		return super.onOptionsItemSelected(item);
	}
	
	private DetailContentData[] parseFromDataseField(final String dbValue,
		int expectedMinResultCount, boolean resultCountIsFixed) {
		String _dbValue = (null != dbValue) ? dbValue : SPACE;
		
		String[] items = _dbValue.split(QueryEntryActivity.ITEM_DELIM);
		ArrayList<String> itemList = new ArrayList<String>();
		
		if (expectedMinResultCount < 1)
			expectedMinResultCount = 1;
		
		if (resultCountIsFixed) {
			for (int i = 0; i < expectedMinResultCount; ++i) {
				itemList.add(null);
			}
		}
		else {
			int count = 0;
			
			if (null == items)
				count = expectedMinResultCount;
			else
				count = (items.length > expectedMinResultCount) ? items.length : expectedMinResultCount;
			
			for (int i = 0; i < count; ++i) {
				itemList.add(null);
			}
		}
		
		if (null != items) {
			int countWithValue = (items.length <= itemList.size()) ? items.length : itemList.size();
			
			for (int i = 0; i < countWithValue; ++i) {
				itemList.set(i, items[i]);
			}
		}
		
		return parseFromDataseField(itemList.toArray(new String[itemList.size()]));
	}
	
	private DetailContentData[] parseFromDataseField(final String[] dbValueArray) {
		ArrayList<DetailContentData> dataList = new ArrayList<DetailContentData>();
		
		for (int i = 0; i < dbValueArray.length; ++i) {
			dataList.add(parseFromDataseField(dbValueArray[i]));
		}
		
		return dataList.toArray(new DetailContentData[dataList.size()]);
	}
	
	private DetailContentData parseFromDataseField(final String dbValue) {
		DetailContentData result = new DetailContentData();
		String[] fieldValues = (null != dbValue) ? dbValue.split(QueryEntryActivity.FIELD_DELIM) : null;		
		int fieldCount = (null != fieldValues) ? fieldValues.length : 0;
		
		final String[] FIELD_NAMES = DETAIL_CONTENT_FIELDS;
		final int EXPECTED_FIELD_COUNT = FIELD_NAMES.length;
		
		if (0 == fieldCount) {
			result.checkBoxFlags &= (~CHKBOX_SELECTED);
			
			for (int i = 1; i < EXPECTED_FIELD_COUNT - 1; ++i) {
				boolean isSpinner = (1 == i % 2);
				
				if (isSpinner)
					result.selectedSpinnerPositions.put(FIELD_NAMES[i], 0);
				else
					result.editTextContents.put(FIELD_NAMES[i], SPACE);
			}
			
			result.editTextContents.put(FIELD_NAMES[EXPECTED_FIELD_COUNT - 1], SPACE);
			
			return result;
		}
		
		if (0 != (com.android_assistant.Integer.parseInt(fieldValues[0], 10, 0) & CHKBOX_SELECTED))
			result.checkBoxFlags |= CHKBOX_SELECTED;
		else
			result.checkBoxFlags &= (~CHKBOX_SELECTED);
		
		for (int i = 1; i < fieldCount - 1; ++i) {
			if (i >= EXPECTED_FIELD_COUNT)
				break;
			
			boolean isSpinner = (1 == i % 2);
			
			if (isSpinner)
				result.selectedSpinnerPositions.put(FIELD_NAMES[i], com.android_assistant.Integer.parseInt(fieldValues[i], 10, 0));
			else
				result.editTextContents.put(FIELD_NAMES[i], fieldValues[i]);
		}
		
		if (fieldCount >= EXPECTED_FIELD_COUNT)
			result.editTextContents.put(FIELD_NAMES[EXPECTED_FIELD_COUNT - 1], fieldValues[EXPECTED_FIELD_COUNT - 1]);
		
		return result;
	}
	
	private String serializeToDatabaseField(final List<DetailContentData> input) {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < input.size(); ++i) {
			result.append(serializeToDatabaseField(input.get(i)));
			result.append(QueryEntryActivity.ITEM_DELIM);
		}
		
		return result.toString();
	}
	
	private String serializeToDatabaseField(final DetailContentData input) {
		StringBuilder result = new StringBuilder();
		
		final String[] FIELD_NAMES = DETAIL_CONTENT_FIELDS;
		
		result.append(input.checkBoxFlags);
		result.append(QueryEntryActivity.FIELD_DELIM);
		
		for (int i = 1; i < FIELD_NAMES.length - 1; ++i) {
			boolean isSpinner = (1 == i % 2);
			String value = null;
			
			if (isSpinner)
				value = String.valueOf(input.selectedSpinnerPositions.get(FIELD_NAMES[i]));
			else
				value = input.editTextContents.get(FIELD_NAMES[i]);
			
			result.append((null == value || 0 == value.length()) ? SPACE : value);
			result.append(QueryEntryActivity.FIELD_DELIM);
		}
		
		result.append(input.editTextContents.get(FIELD_NAMES[FIELD_NAMES.length - 1]));
		
		return result.toString();
	}
	
	private void fillDetailTitles() {
		int[][] detailItemResIds = getDetailItemResourceIds();
		
		for (int i = 0; i < detailItemResIds.length; ++i) {
			if (detailContentIsNotUsed(i))
				continue;
			
			ArrayList<String> titleList = new ArrayList<String>();
			ListView lsvTitle = (ListView) findViewById(detailItemResIds[i][1]);
			DetailTitleAdapter titleAdapter = new DetailTitleAdapter(this, titleList);
			
			titleList.add(getString(detailItemResIds[i][0]));
			
			lsvTitle.setAdapter(titleAdapter);
		}
	}
	
	private boolean detailContentIsSimple(int contentIndex) {
		return (MEDICINE_COLUMN_INDEX_NAME == contentIndex
			|| MEDICINE_COLUMN_INDEX_ALIASES == contentIndex
			//|| MEDICINE_COLUMN_INDEX_CATEGORY == contentIndex
			|| MEDICINE_COLUMN_INDEX_EFFECTS == contentIndex
			|| MEDICINE_COLUMN_INDEX_ACTIONS_AND_INDICATIONS == contentIndex
			|| MEDICINE_COLUMN_INDEX_DETAILS == contentIndex
			|| MEDICINE_COLUMN_INDEX_COMMON_PRESCRIPTIONS == contentIndex
			|| MEDICINE_COLUMN_INDEX_COMMON_PARTNERS == contentIndex
			|| MEDICINE_COLUMN_INDEX_SIMILAR_MEDICINES == contentIndex
			|| MEDICINE_COLUMN_INDEX_DOSAGE_REFERENCE == contentIndex
			|| MEDICINE_COLUMN_INDEX_CONTRAINDICATIONS == contentIndex
			|| MEDICINE_COLUMN_INDEX_REMARKS == contentIndex);
	}
	
	private boolean detailContentIsSimple(String contentName) {
		return (MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_NAME].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_ALIASES].equals(contentName)
			//|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_CATEGORY].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_EFFECTS].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_ACTIONS_AND_INDICATIONS].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_DETAILS].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_COMMON_PRESCRIPTIONS].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_COMMON_PARTNERS].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_SIMILAR_MEDICINES].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_DOSAGE_REFERENCE].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_CONTRAINDICATIONS].equals(contentName)
			|| MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_REMARKS].equals(contentName));
	}
	
	private boolean detailContentIsNotUsed(int contentIndex) {
		return (MEDICINE_COLUMN_INDEX_MOTION_FORMS_OF_ACTION == contentIndex);
	}
	
	private int[][] getDetailItemResourceIds() {
		final int[][] RES_IDS = {
			{ R.string.name, R.id.lsv_title_medicine_name, R.id.lsv_content_medicine_name },
			{ R.string.alias, R.id.lsv_title_medicine_aliases, R.id.lsv_content_medicine_aliases },
			{ R.string.category, R.id.lsv_title_medicine_category, R.id.lsv_content_medicine_category },
			{ R.string.medicine_nature, R.id.lsv_title_medicine_nature, R.id.lsv_content_medicine_nature },
			{ R.string.medicine_taste, R.id.lsv_title_medicine_tastes, R.id.lsv_content_medicine_tastes },
			{ R.string.channel_tropism, R.id.lsv_title_channel_tropism, R.id.lsv_content_channel_tropism },
			{ R.string.life_fundamental, R.id.lsv_title_relations_with_life_fundamentals, R.id.lsv_content_relations_with_life_fundamentals },
			{ R.string.motion_form, R.id.lsv_title_motion_form, R.id.lsv_content_motion_form },
			{ R.string.medicine_effect, R.id.lsv_title_medicine_effects, R.id.lsv_content_medicine_effects },
			{ R.string.medicine_action_and_indication, R.id.lsv_title_medicine_actions_and_indications, R.id.lsv_content_medicine_actions_and_indications },
			{ R.string.medication_details, R.id.lsv_title_medicine_details, R.id.lsv_content_medicine_details },
			{ R.string.common_prescriptions, R.id.lsv_title_common_prescriptions, R.id.lsv_content_common_prescriptions },
			{ R.string.common_medicine_partners, R.id.lsv_title_common_medicine_partners, R.id.lsv_content_common_medicine_partners },
			{ R.string.similar_medicines, R.id.lsv_title_similar_medicines, R.id.lsv_content_similar_medicines },
			{ R.string.dosage_reference, R.id.lsv_title_dosage_reference, R.id.lsv_content_dosage_reference },
			{ R.string.contraindications, R.id.lsv_title_medicine_contraindications, R.id.lsv_content_medicine_contraindications },
			{ R.string.reference_material, R.id.lsv_title_medicine_reference_material, R.id.lsv_content_medicine_reference_material },
			{ R.string.remarks, R.id.lsv_title_medicine_remarks, R.id.lsv_content_medicine_remarks }
		};
		
		return RES_IDS;
	}
	
	private void fillDetailContentTemplates() {
		// Simple items, including name, aliases, category, details, common_prescriptions,
		// common_partners, similar_medicines, dosage_reference, contraindications
		// and remarks, need not filling up.
		
		DetailContentTemplate template = null;
		DetailContentTemplate[] array = mDetailContentTemplates;
		HashMap<String, DetailContentTemplate> map = mMapDetailContentTemplates;
		
		for (int i = 0; i < array.length; ++i) {
			map.put(MEDICINE_COLUMNS[i], array[i]);
		}
		
		DbHelper dbHelper = mDbHelper;
		String spaceString = getString(R.string.space);
		String unknownString = getString(R.string.unknown);
		String hintPleaseSelect = getString(R.string.please_select);
		//String hintPleaseSelectOrCustomize = getString(R.string.please_select_or_customize);
		String[] levelWords = dbHelper.queryAttributeNames(R.string.attr_table_prefix_level_word,
			spaceString);		

		int[][] integers = {
			// column index, isFixed, minRecords, checkBoxFlags
			{ MEDICINE_COLUMN_INDEX_CATEGORY, 1, 1, 0 },
			{ MEDICINE_COLUMN_INDEX_NATURE, 1, 1, 0 },
			{ MEDICINE_COLUMN_INDEX_TASTES, 1, 7, CHKBOX_VISIBLE | CHKBOX_CLICKABLE },
			{ MEDICINE_COLUMN_INDEX_CHANNEL_TROPISM, 1, 12, CHKBOX_VISIBLE | CHKBOX_CLICKABLE },
			{ MEDICINE_COLUMN_INDEX_LIFE_FUNDAMENTALS, 1, 5, CHKBOX_VISIBLE | CHKBOX_CLICKABLE },
			{ MEDICINE_COLUMN_INDEX_MOTION_FORMS_OF_ACTION, 1, 6, 0 },
			{ MEDICINE_COLUMN_INDEX_EFFECTS, 0, 1, 0 },
			{ MEDICINE_COLUMN_INDEX_ACTIONS_AND_INDICATIONS, 0, 1, 0 },
			{ MEDICINE_COLUMN_INDEX_REFERENCE_MATERIAL, 0, 1, 0 }
		};
		
		String[] spinnerNames = DETAIL_CONTENT_SPINNER_NAMES;
		String[][][] spinnerItems = {
			// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
			{ null, null, null, dbHelper.queryMedicineCategories(unknownString), null, null },
			{ null, null, levelWords, dbHelper.queryAttributeNames(R.string.attr_table_prefix_medicine_nature, hintPleaseSelect), null, null },
			{ null, null, levelWords, /*null*/dbHelper.queryAttributeNames(R.string.attr_table_prefix_medicine_taste, ITEM_READ_ONLY), null, null },
			{ null, null, null, /*null*/dbHelper.queryAttributeNames(R.string.attr_table_prefix_channel_tropism, ITEM_READ_ONLY), null, null },
			{ null, null, dbHelper.queryAttributeNames(R.string.attr_table_prefix_action_verb, hintPleaseSelect), /*null*/dbHelper.queryAttributeNames(R.string.attr_table_prefix_life_fundamental, ITEM_READ_ONLY), null, null },
			{ null, null, null, null/*dbHelper.queryAttributeNames(R.string.attr_table_prefix_motion_form, hintPleaseSelect)*/, null, null },
			{ null, null, null, null/*dbHelper.queryAttributeNames(R.string.attr_table_prefix_medicine_effect, hintPleaseSelectOrCustomize)*/, null, null },
			{ null, null, null, null/*dbHelper.queryAttributeNames(R.string.attr_table_prefix_medicine_action_and_indication, hintPleaseSelectOrCustomize)*/, null, null },
			{ null, null, null, null/*dbHelper.queryReferenceMaterialNames()*/, null, null }
		};
		
		String[] editTextNames = DETAIL_CONTENT_EDIT_TEXT_NAMES;
		String[][] defaultEditTextValues = {
			// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null/*ITEM_READ_ONLY*/, null, null, null },
			{ null, null, null, null/*ITEM_READ_ONLY*/, null, null, null },
			{ null, null, null, null/*ITEM_READ_ONLY*/, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, getString(R.string.hint_input_extra_contents_if_necessary) },
			{ null, null, null, null, null, null, getString(R.string.hint_input_extra_contents_if_necessary) },
			{ null, null, null, null, null, null, getString(R.string.reference_material_contents_example) }
		};
		
		for (int i = 0; i < integers.length; ++i) {
			int index = integers[i][0];
			
			template = array[index];
			
			template.isFixed = (0 != integers[i][1]);
			template.minRecords = integers[i][2];
			template.checkBoxFlags = integers[i][3];
			
			for (int j = 0; j < spinnerNames.length; ++j) {
				template.mapSpinnerItems.put(spinnerNames[j], spinnerItems[i][j]);
			}
			
			for (int j = 0; j < editTextNames.length; ++j) {
				template.mapDefaultEditTextValues.put(editTextNames[j], defaultEditTextValues[i][j]);
			}
		}
	}
	
	private void fillDetailContents() {
		HashMap<String, String> mapDetails = mDetailContentFromDb;
		int[][] detailItemResIds = getDetailItemResourceIds();
		//String hintPleaseSelect = getString(R.string.please_select);
		String[] tastes = mDbHelper.queryAttributeNames(R.string.attr_table_prefix_medicine_taste, null);
		String[] channelTropism = mDbHelper.queryAttributeNames(R.string.attr_table_prefix_channel_tropism, null);
		String[] lifeFundamental = mDbHelper.queryAttributeNames(R.string.attr_table_prefix_life_fundamental, null);
		String referenceMaterial = mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_REFERENCE_MATERIAL]);
		String SPN_VALUE_SHORT = DETAIL_CONTENT_FIELDS[ITEM_COLUMN_INDEX_SPN_VALUE];
		String ETX_VALUE_SHORT = DETAIL_CONTENT_FIELDS[ITEM_COLUMN_INDEX_ETX_VALUE_SHORT];
		
		if (null == referenceMaterial || 0 == referenceMaterial.length())
			referenceMaterial = getString(R.string.reference_material_contents_example);
		
		DetailContentData[] natureData = parseFromDataseField(
			mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_NATURE]), 1, true);
		
		DetailContentData[] tastesData = parseFromDataseField(
			mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_TASTES]), tastes.length, true);
		
		DetailContentData[] channelTropismData = parseFromDataseField(
			mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_CHANNEL_TROPISM]), channelTropism.length, true);
		
		DetailContentData[] lifeFundamentalData = parseFromDataseField(
			mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_LIFE_FUNDAMENTALS]), lifeFundamental.length, true);
		
		for (int i = 0; i < natureData.length; ++i) {
			natureData[i].medicineColumnIndex = MEDICINE_COLUMN_INDEX_NATURE;
		}
		
		for (int i = 0; i < tastesData.length; ++i) {
			tastesData[i].medicineColumnIndex = MEDICINE_COLUMN_INDEX_TASTES;
			tastesData[i].selectedSpinnerPositions.put(SPN_VALUE_SHORT, i + 1);
			tastesData[i].editTextContents.put(ETX_VALUE_SHORT, tastes[i]);
		}
		
		for (int i = 0; i < channelTropismData.length; ++i) {
			channelTropismData[i].medicineColumnIndex = MEDICINE_COLUMN_INDEX_CHANNEL_TROPISM;
			channelTropismData[i].selectedSpinnerPositions.put(SPN_VALUE_SHORT, i + 1);
			channelTropismData[i].editTextContents.put(ETX_VALUE_SHORT, channelTropism[i]);
		}
		
		for (int i = 0; i < lifeFundamentalData.length; ++i) {
			lifeFundamentalData[i].medicineColumnIndex = MEDICINE_COLUMN_INDEX_LIFE_FUNDAMENTALS;
			lifeFundamentalData[i].selectedSpinnerPositions.put(SPN_VALUE_SHORT, i + 1);
			lifeFundamentalData[i].editTextContents.put(ETX_VALUE_SHORT, lifeFundamental[i]);
		}
		
		DetailContentData[][] contentData = {
			// medicineColumnIndex, checkBoxFlags,
			// selectedSpinnerPositions,
			// editTextContents

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_NAME, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_NAME]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_ALIASES, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_ALIASES]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_CATEGORY, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, Integer.parseInt(mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_CATEGORY])), 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, null } )
			},

			natureData,

			tastesData,

			channelTropismData,

			lifeFundamentalData,

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_MOTION_FORMS_OF_ACTION, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, null } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_EFFECTS, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_EFFECTS]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_ACTIONS_AND_INDICATIONS, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_ACTIONS_AND_INDICATIONS]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_DETAILS, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_DETAILS]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_COMMON_PRESCRIPTIONS, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_COMMON_PRESCRIPTIONS]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_COMMON_PARTNERS, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_COMMON_PARTNERS]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_SIMILAR_MEDICINES, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_SIMILAR_MEDICINES]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_DOSAGE_REFERENCE, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_DOSAGE_REFERENCE]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_CONTRAINDICATIONS, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_CONTRAINDICATIONS]) } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_REFERENCE_MATERIAL, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, referenceMaterial } )
			},

			{
				new DetailContentData(MEDICINE_COLUMN_INDEX_REMARKS, 0,
					// spnKey, spnValuePrefix_1, spnValuePrefix_2, spnValue, spnValueSuffix_1, spnValueSuffix_2
					new int[]{ 0, 0, 0, 0, 0, 0 },
					// etxKey, etxValuePrefix_1, etxValuePrefix_2, etxValueShort, etxValueSuffix_1, etxValueSuffix_2, etxValueLong
					new String[] { null, null, null, null, null, null, mapDetails.get(MEDICINE_COLUMNS[MEDICINE_COLUMN_INDEX_REMARKS]) } )
			}
		};
		
		for (int i = 0; i < MEDICINE_COLUMNS.length; ++i) {
			if (detailContentIsNotUsed(i))
				continue;
			
			ArrayList<DetailContentData> contentsList = new ArrayList<DetailContentData>();
			DetailContentAdapter contentsAdapter = new DetailContentAdapter(this, contentsList);
			ListView lsvContents = (ListView) findViewById(detailItemResIds[i][2]);
			
			for (int j = 0; j < contentData[i].length; ++j) {
				contentsList.add(contentData[i][j]);
			}
			
			lsvContents.setAdapter(contentsAdapter);
		}
	}
	
	private class DetailTitleAdapter extends BaseAdapter {

		private final Context context;
		private final List<String> itemList;
		private final LayoutInflater inflater;

		public DetailTitleAdapter(Context context, List<String> itemList) {
			super();
			this.itemList = itemList;
			this.context = context;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return itemList.size();
		}

		@Override
		public Object getItem(int position) {
			return itemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;

			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.details_item_title, null);
				holder.name = (TextView) convertView.findViewById(R.id.txv_details_title);
				holder.iconAdd = (ImageView) convertView.findViewById(R.id.imgv_add_details_icon);
				holder.iconDelete = (ImageView) convertView.findViewById(R.id.imgv_delete_details_icon);
				holder.btnConfirm = (Button) convertView.findViewById(R.id.btn_confirm_details_change);
				holder.btnCancel = (Button) convertView.findViewById(R.id.btn_cancel_details_change);
				convertView.setTag(holder);
			}
			else {
				holder = (ViewHolder) convertView.getTag();
			}

			String item = itemList.get(position);
			
			holder.name.setText(item);
			com.android_assistant.TextView.setDefaultTextShadow(holder.name);
			
			/*holder.iconAdd.setVisibility(TextView.VISIBLE);
			holder.iconDelete.setVisibility(TextView.VISIBLE);
			
			holder.btnConfirm.setVisibility(TextView.VISIBLE);
			holder.btnCancel.setVisibility(TextView.VISIBLE);*/

			return convertView;
		}

		private class ViewHolder {
			TextView name;
			ImageView iconAdd;
			ImageView iconDelete;
			Button btnConfirm;
			Button btnCancel;
		}
	}
	
	private class DetailContentData {
		public int medicineColumnIndex;
		public int checkBoxFlags;
		public HashMap<String, Integer> selectedSpinnerPositions;
		public HashMap<String, String> editTextContents;
		public boolean spinnersEnabled;
		public boolean editTextsEnabled;
		
		public DetailContentData() {
			selectedSpinnerPositions = new HashMap<String, Integer>();
			editTextContents = new HashMap<String, String>();
			spinnersEnabled = false;
			editTextsEnabled = false;
		}
		
		public DetailContentData(int medicineColumnIndex, int checkBoxFlags,
			int[] selectedSpinnerPositions, String[] editTextContents) {
			this.medicineColumnIndex = medicineColumnIndex;
			this.checkBoxFlags = checkBoxFlags;
			this.spinnersEnabled = false;
			this.editTextsEnabled = false;
			this.selectedSpinnerPositions = new HashMap<String, Integer>();
			for (int i = 0; i < DETAIL_CONTENT_SPINNER_NAMES.length; ++i) {
				this.selectedSpinnerPositions.put(DETAIL_CONTENT_SPINNER_NAMES[i],
					selectedSpinnerPositions[i]);
			}
			this.editTextContents = new HashMap<String, String>();
			for (int i = 0; i < DETAIL_CONTENT_EDIT_TEXT_NAMES.length; ++i) {
				this.editTextContents.put(DETAIL_CONTENT_EDIT_TEXT_NAMES[i],
					editTextContents[i]);
			}
		}
	}
	
	private class DetailContentTemplate {
		public boolean isFixed;
		public int minRecords;
		public int checkBoxFlags;
		public HashMap<String, String[]> mapSpinnerItems;
		public HashMap<String, String> mapDefaultEditTextValues;
		
		public DetailContentTemplate() {
			mapSpinnerItems = new HashMap<String, String[]>();
			mapDefaultEditTextValues = new HashMap<String, String>();
		}
	}
	
	private class DetailContentAdapter extends BaseAdapter {

		private final Context mContext;
		private final List<DetailContentData> mItemList;
		private final LayoutInflater mInflater;

		public DetailContentAdapter(Context context, List<DetailContentData> itemList) {
			super();
			this.mItemList = itemList;
			this.mContext = context;
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return mItemList.size();
		}
		
		public List<DetailContentData> getItemList() {
			return mItemList;
		}

		@Override
		public Object getItem(int position) {
			return mItemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			ViewHolder holder = null;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.details_item_content, parent, false);
				holder = createViewHolder(convertView);
				convertView.setTag(holder);
			}
			else {
				holder = (ViewHolder) convertView.getTag();
			}

			setViewHolder(holder, position);

			return convertView;
		}
		
		private ViewHolder createViewHolder(View convertView) {
			if (convertView == null)
				convertView = mInflater.inflate(R.layout.details_item_content, null);
			
			ViewHolder holder = new ViewHolder();
			
			holder.checkBox = (CheckBox) convertView.findViewById(R.id.chkbox_details_item);
			holder.spnKey = (Spinner) convertView.findViewById(R.id.spn_details_key);
			holder.etxKey = (EditText) convertView.findViewById(R.id.etx_details_key);
			holder.spnValuePrefix_1 = (Spinner) convertView.findViewById(R.id.spn_details_value_prefix_1);
			holder.etxValuePrefix_1 = (EditText) convertView.findViewById(R.id.etx_details_value_prefix_1);
			holder.spnValuePrefix_2 = (Spinner) convertView.findViewById(R.id.spn_details_value_prefix_2);
			holder.etxValuePrefix_2 = (EditText) convertView.findViewById(R.id.etx_details_value_prefix_2);
			holder.spnValue = (Spinner) convertView.findViewById(R.id.spn_details_value);
			holder.etxValueShort = (EditText) convertView.findViewById(R.id.etx_details_value_short_texts);
			holder.spnValueSuffix_1 = (Spinner) convertView.findViewById(R.id.spn_details_value_suffix_1);
			holder.etxValueSuffix_1 = (EditText) convertView.findViewById(R.id.etx_details_value_suffix_1);
			holder.spnValueSuffix_2 = (Spinner) convertView.findViewById(R.id.spn_details_value_suffix_2);
			holder.etxValueSuffix_2 = (EditText) convertView.findViewById(R.id.etx_details_value_suffix_2);
			holder.etxValueLong = (EditText) convertView.findViewById(R.id.etx_details_value_long_texts);
			
			return holder;
		}
		
		private void setViewHolder(final ViewHolder holder, final int position) {
			final DetailContentData item = mItemList.get(position);
			
			if (null == item)
				return;
			
			Spinner[] holderSpinners = {
				holder.spnKey,
				holder.spnValuePrefix_1,
				holder.spnValuePrefix_2,
				holder.spnValue,
				holder.spnValueSuffix_1,
				holder.spnValueSuffix_2
			};
			
			EditText[] holderEditTexts = {
				holder.etxKey,
				holder.etxValuePrefix_1,
				holder.etxValuePrefix_2,
				holder.etxValueShort,
				holder.etxValueSuffix_1,
				holder.etxValueSuffix_2,
				holder.etxValueLong
			};
			
			/*holder.etxValueLong.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					mItemList.get(position).editTextContents.put("etxValueLong", s.toString());
				}
			});*/
			
			// case 1: for simple contents
			if (detailContentIsSimple(item.medicineColumnIndex)) {
				holder.medicineColumnIndex = item.medicineColumnIndex;
				holder.checkBox.setVisibility(TextView.GONE);
				
				holder.etxValueLong.setEnabled(item.editTextsEnabled);
				holder.etxValueLong.setText(item.editTextContents.get("etxValueLong"));
				com.android_assistant.TextView.setDefaultTextShadow(holder.etxValueLong);
				holder.etxValueLong.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						mItemList.get(position).editTextContents.put("etxValueLong", s.toString());
					}
				});
				
				for (int i = 0; i < holderSpinners.length; ++i) {
					holderSpinners[i].setVisibility(TextView.GONE);
					
					holderEditTexts[i].setVisibility(TextView.GONE);
				}
				
				return;
			}
			
			/*
			 * case 2: for complicated contents
			 */
			
			DetailContentTemplate template = mMapDetailContentTemplates.get(
				MEDICINE_COLUMNS[item.medicineColumnIndex]);
			
			holder.medicineColumnIndex = item.medicineColumnIndex;

			holder.checkBox.setVisibility((0 != (template.checkBoxFlags & CHKBOX_VISIBLE))
				? TextView.VISIBLE : TextView.GONE);
			holder.checkBox.setClickable(0 != (item.checkBoxFlags & CHKBOX_CLICKABLE));
			holder.checkBox.setChecked(0 != (item.checkBoxFlags & CHKBOX_SELECTED));
			holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if (!buttonView.isPressed())
						return;
					
					if (isChecked)
						item.checkBoxFlags |= CHKBOX_SELECTED;
					else
						item.checkBoxFlags &= (~CHKBOX_SELECTED);
				}
				
			});
			
			for (int i = 0; i < holderSpinners.length; ++i) {
				final String spinnerName = DETAIL_CONTENT_SPINNER_NAMES[i];
				String[] spinnerItems = template.mapSpinnerItems.get(spinnerName);
				
				if (null == spinnerItems) {
					holderSpinners[i].setVisibility(TextView.GONE);
					continue;
				}
				
				holderSpinners[i].setAdapter(new ArrayAdapter<String>(mContext,
					R.drawable.default_spinner_text, spinnerItems));
				Integer selectedPosition = item.selectedSpinnerPositions.get(spinnerName);
				holderSpinners[i].setSelection((null != selectedPosition) ? selectedPosition : 0);
				holderSpinners[i].setEnabled(ITEM_READ_ONLY != spinnerItems[0] && item.spinnersEnabled);
				holderSpinners[i].setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> adapter, View view,
							int position, long id) {
						item.selectedSpinnerPositions.put(spinnerName, position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> adapter) {
						;
					}
				});
			}
			
			for (int i = 0; i < holderEditTexts.length; ++i) {
				final String editTextName = DETAIL_CONTENT_EDIT_TEXT_NAMES[i];
				String editTextValue = template.mapDefaultEditTextValues.get(editTextName);
				
				if (null == editTextValue) {
					holderEditTexts[i].setVisibility(TextView.GONE);
					continue;
				}
				
				holderEditTexts[i].setEnabled(!editTextValue.equals(ITEM_READ_ONLY) && item.editTextsEnabled);
				holderEditTexts[i].setText(item.editTextContents.get(editTextName));
				com.android_assistant.TextView.setDefaultTextShadow(holderEditTexts[i]);
				holderEditTexts[i].addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						/*mItemList.get(position)*/item.editTextContents.put(editTextName, s.toString());
					}
				});
			}
		}

		private class ViewHolder {
			int medicineColumnIndex;
			CheckBox checkBox;
			Spinner spnKey;
			EditText etxKey;
			Spinner spnValuePrefix_1;
			EditText etxValuePrefix_1;
			Spinner spnValuePrefix_2;
			EditText etxValuePrefix_2;
			Spinner spnValue;
			EditText etxValueShort;
			Spinner spnValueSuffix_1;
			EditText etxValueSuffix_1;
			Spinner spnValueSuffix_2;
			EditText etxValueSuffix_2;
			EditText etxValueLong;
		}
	}
}
